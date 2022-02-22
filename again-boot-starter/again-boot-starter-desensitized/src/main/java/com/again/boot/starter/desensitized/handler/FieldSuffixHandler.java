package com.again.boot.starter.desensitized.handler;

import com.again.boot.starter.desensitized.annotation.FieldSuffix;
import com.again.boot.starter.desensitized.utils.R;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Collection;

/**
 * @author create by 罗英杰 on 2021/11/29
 * @description:
 */
@Aspect
public class FieldSuffixHandler {

	@AfterReturning(returning = "data",
			pointcut = "@annotation(com.again.boot.starter.desensitized.annotation.FieldSuffix)")
	public Object around(JoinPoint joinPoint, Object data) throws Throwable {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		FieldSuffix fieldSuffix = signature.getMethod().getAnnotation(FieldSuffix.class);
		if (data instanceof R) {
			R result = (R) data;
			this.setFieldValueForCollection(fieldSuffix, result.getData());
		}

		return data;
	}

	/**
	 * 查询并赋值操作
	 * @param data 目标数据
	 */
	private void setFieldValueForCollection(FieldSuffix fieldSuffix, Object data) throws Exception {
		Class targetClass = fieldSuffix.targetClass();
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
				setFieldValueForCollection(fieldSuffix, next);
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
					setFieldValueForCollection(fieldSuffix, dataIn);
				}
				else {
					if (String.class != field.getType() || field.getAnnotation(FieldSuffix.class) == null) {
						continue;
					}
					// 暴力拆解
					field.setAccessible(true);
					// String targetField = field.getName();// realName
					String value = (String) field.get(data);
					String[] filedSuffixes = fieldSuffix.value();
					for (String filedSuffix : filedSuffixes) {
						value = value.replaceAll(filedSuffix, "");
					}
					field.set(data, value);
				}

			}
		}

	}

}
