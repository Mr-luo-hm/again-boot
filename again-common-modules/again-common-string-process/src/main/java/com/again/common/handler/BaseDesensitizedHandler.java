package com.again.common.handler;

import com.again.common.annotation.Desensitized;
import com.again.common.enumerate.SensitiveTypeEnum;
import com.again.common.utils.DesensitizedUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Field;
import java.util.Collection;

/**
 * @author create by 罗英杰 on 2021/11/29
 * @description:
 */
@Aspect
public class BaseDesensitizedHandler {

	/**
	 * Desensitization base class
	 * @param o data
	 * @return Examples of things to desensitize if (data instanceof R) { R result = (R)
	 * data; this.setFieldValueForCollection(desensitized, result.getData()); }
	 *
	 */
	public Object vestIn(Object o) {
		return o;
	}

	/**
	 * Desensitization
	 * @param type Desensitization type
	 * @param value before desensitization field
	 * @return after desensitization field
	 */
	public String desensitized(SensitiveTypeEnum type, String value) {
		switch (type) {
		case MOBILE_PHONE:
			return DesensitizedUtils.desensitizedPhoneNumber(value);
		case CHINESE_NAME:
			return DesensitizedUtils.chineseNameDesensitization(value);
		case ID_CARD:
			return DesensitizedUtils.desensitizedIdCard(value);
		case ADDRESS:
			return DesensitizedUtils.desensitizedAddress(value);
		case EMAIL:
			return DesensitizedUtils.emailDesensitization(value);
		case BANK_CARD:
			return DesensitizedUtils.acctNoDesensitization(value);
		default:
			return value;
		}
	}

	@AfterReturning(returning = "data", pointcut = "@annotation(com.again.common.annotation.Desensitized)")
	public Object around(JoinPoint joinPoint, Object data) throws Throwable {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		// Gets the label of the method
		Desensitized desensitized = signature.getMethod().getAnnotation(Desensitized.class);
		this.setFieldValueForCollection(desensitized, vestIn(data));
		return data;
	}

	/**
	 * Query and assign operations
	 * @param data target data
	 */
	private void setFieldValueForCollection(Desensitized desensitized, Object data) throws Exception {
		Class<?> targetClass = desensitized.targetClass();
		// Get the field if you're sure it's not a collection
		if (null == data) {
			return;
		}
		Class<?> clazz = data.getClass();
		if (clazz == Field.class) {
			clazz = ((Field) data).getType();
		}
		if (Collection.class.isAssignableFrom(clazz) || clazz.isArray()) {
			// If it is a collection, it is recycled
			if (data instanceof Collection) {
				Collection collection = (Collection) data;
				// I don't want to call it null because DATA has already judged it
				if (collection.isEmpty()) {
					return;
				}
				for (Object next : collection) {
					setFieldValueForCollection(desensitized, next);
				}
			}
		}
		else {
			if (clazz != targetClass) {
				return;
			}
			Field[] declaredFields = clazz.getDeclaredFields();
			for (Field field : declaredFields) {
				field.setAccessible(true);
				// If the field is empty, the next one is not processed
				if (null == field.get(data)) {
					continue;
				}
				// Determines whether the field type is a collection
				if (Collection.class.isAssignableFrom(field.getType()) || field.getType().isArray()) {
					// If it is a collection, it is recycled
					Object dataIn = field.get(data);
					setFieldValueForCollection(desensitized, dataIn);
				}
				else {
					Desensitized desensitization;
					if (String.class != field.getType()
							|| (desensitization = field.getAnnotation(Desensitized.class)) == null) {
						continue;
					}
					// 暴力拆解
					field.setAccessible(true);
					// String targetField = field.getName();// realName
					String value = (String) field.get(data);
					SensitiveTypeEnum type = desensitization.type();
					// 这里如果嫌长就放到util中
					field.set(data, desensitized(type, value));
				}
			}
		}
	}

}
