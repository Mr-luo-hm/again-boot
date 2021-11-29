package com.again.extend.wipe.data.annotation;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author create by 罗英杰 on 2021/11/12
 * @description:
 */
@Target({ METHOD, FIELD, CONSTRUCTOR, PARAMETER, TYPE })
@Retention(RUNTIME)
@Documented
@Inherited
public @interface WipeData {

	/**
	 * 去除字段中得某些数据
	 * @return
	 */
	String value() default "";

}
