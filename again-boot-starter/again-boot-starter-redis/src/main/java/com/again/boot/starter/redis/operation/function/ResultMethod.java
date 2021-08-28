package com.again.boot.starter.redis.operation.function;

/**
 * @author create by 罗英杰 on 2021/8/28
 * @description:
 */
@FunctionalInterface
public interface ResultMethod<T> {

	/**
	 * 执行并返回一个结果
	 * @return
	 */
	T run();

}