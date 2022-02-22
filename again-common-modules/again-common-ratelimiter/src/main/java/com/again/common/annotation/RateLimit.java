package com.again.common.annotation;

import java.lang.annotation.*;

/**
 * @author create by 罗英杰 on 2022/1/8
 * @description:
 */
@Target(value = { ElementType.METHOD })
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimit {

	/**
	 * 时间窗口流量数量
	 * @return rate
	 */
	int rate();

	/**
	 * 时间窗口流量数量表达式
	 * @return rateExpression
	 */
	String rateExpression() default "";

	/**
	 * 时间窗口，最小单位秒，如 2s，2h , 2d
	 * @return rateInterval
	 */
	int rateInterval();

	/**
	 * 获取key
	 * @return keys
	 */
	String key() default "";

	/**
	 * 限流后的自定义回退后的拒绝逻辑
	 * @return fallback
	 */
	String fallbackFunction() default "";

	/**
	 * 自定义业务 key 的 Function
	 * @return key
	 */
	String customKeyFunction() default "";

}
