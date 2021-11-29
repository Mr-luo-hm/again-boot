package com.again.boot.starter.desensitized.annotation;

import com.again.boot.starter.desensitized.enumerate.SensitiveTypeEnum;

import java.lang.annotation.*;

/**
 * @author create by 罗英杰 on 2021/11/26
 * @description: 脱敏规则
 */
@Target({ ElementType.FIELD, ElementType.METHOD }) // 必须是方法配合字段同时注解
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Desensitized {

	// 脱敏类型(规则)
	SensitiveTypeEnum type() default SensitiveTypeEnum.NO;

	// 被脱敏的class
	Class<?> targetClass() default Void.class;

}