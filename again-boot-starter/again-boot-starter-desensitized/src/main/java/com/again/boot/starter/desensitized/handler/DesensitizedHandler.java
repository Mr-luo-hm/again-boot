package com.again.boot.starter.desensitized.handler;

import com.again.boot.starter.desensitized.annotation.Desensitized;
import com.again.boot.starter.desensitized.enumerate.SensitiveTypeEnum;
import com.again.boot.starter.desensitized.utils.DesensitizedUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Field;
import java.util.Collection;

/**
 * @author create by 罗英杰 on 2021/11/26
 * @description:
 */
@Aspect
public class DesensitizedHandler {

	public Object vestIn(Object o) {
		return o;
	}

	@AfterReturning(returning = "data",
			pointcut = "@annotation(com.again.boot.starter.desensitized.annotation.Desensitized)")
	public Object around(JoinPoint joinPoint, Object data) throws Throwable {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		// 获取方法的标签
		Desensitized desensitized = signature.getMethod().getAnnotation(Desensitized.class);
		// 这里有局限性 只能是满足统一返回的类才能被用于检查

		/*
		 * if (data instanceof R) { R result = (R) data;
		 * this.setFieldValueForCollection(desensitized, result.getData()); }else if (data
		 * != null){ this.setFieldValueForCollection(desensitized, vestIn(data)); } else {
		 * return data; }
		 */
		this.setFieldValueForCollection(desensitized, vestIn(data));
		return data;
	}

	/**
	 * 查询并赋值操作
	 * @param data 目标数据
	 */
	private void setFieldValueForCollection(Desensitized desensitized, Object data) throws Exception {
		Class targetClass = desensitized.targetClass();
		// 确定不是集合 就直接获取字段
		if (null == data) {
			return;
		}
		Class clazz = data.getClass();
		if (clazz == Field.class) {
			clazz = ((Field) data).getType();
		}
		if (Collection.class.isAssignableFrom(clazz) || clazz.isArray()) {
			// 如果是集合 则循环获取
			Collection collection = (Collection) data;
			// 这里不要判断空 因为 data已经判断过
			if (collection.isEmpty()) {
				return;
			}
			for (Object next : collection) {
				setFieldValueForCollection(desensitized, next);
			}
		}
		else {
			if (clazz != targetClass) {
				return;
			}
			Field[] declaredFields = clazz.getDeclaredFields();
			for (Field field : declaredFields) {
				field.setAccessible(true);
				// 如果字段是空的 就不处理下一个
				if (null == field.get(data)) {
					continue;
				}
				// 判断字段类型是否为集合
				if (Collection.class.isAssignableFrom(field.getType()) || field.getType().isArray()) {
					// 如果是集合 则循环获取
					Object dataIn = field.get(data);
					setFieldValueForCollection(desensitized, dataIn);
				}
				else {
					Desensitized desensitization;
					if (String.class != field.getType()
							|| (desensitization = field.getAnnotation(Desensitized.class)) == null) {
						continue;
					}
					field.setAccessible(true);// 暴力拆解
					// String targetField = field.getName();// realName
					String value = (String) field.get(data);
					SensitiveTypeEnum type = desensitization.type();
					// 这里如果嫌长就放到util中
					switch (type) {
					case MOBILE_PHONE:
						value = DesensitizedUtils.desensitizedPhoneNumber(value);
						break;
					default:
					}
					field.set(data, value);
				}

			}
		}

	}

}
