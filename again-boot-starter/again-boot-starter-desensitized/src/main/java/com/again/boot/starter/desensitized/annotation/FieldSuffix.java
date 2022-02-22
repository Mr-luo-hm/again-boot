package com.again.boot.starter.desensitized.annotation;

import java.lang.annotation.*;

/**
 * @author create by 罗英杰 on 2021/11/29
 * @description:
 */
@Target({ ElementType.FIELD, ElementType.METHOD }) // 必须是方法配合字段同时注解
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface FieldSuffix {

	// 被脱敏的class
	Class<?> targetClass() default Void.class;

	// 脱敏类型(规则)
	String[] value() default "";

}
