package com.again.boot.starter.redis.operation;

/**
 * @author create by 罗英杰 on 2021/8/28
 * @description:
 */
public enum OpType {

	/**
	 * 无缓存，直接执行原始方法
	 */
	ORIGINAL,

	/**
	 * 先查询缓存，如果有则直接返回 若没有，则执行目标方法，获取返回值后存入缓存
	 */
	CACHED,

	/**
	 * 执行目标方法后，获取返回值存入缓存
	 */
	PUT,

	/**
	 * 执行目标方法后，删除缓存
	 */
	DEL;

}
