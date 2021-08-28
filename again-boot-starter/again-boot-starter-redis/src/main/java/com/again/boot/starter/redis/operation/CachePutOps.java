package com.again.boot.starter.redis.operation;

import org.aspectj.lang.ProceedingJoinPoint;

import java.util.function.Consumer;

/**
 * @author create by 罗英杰 on 2021/8/28
 * @description:
 */
public class CachePutOps extends AbstractCacheOps {

	public CachePutOps(ProceedingJoinPoint joinPoint, Consumer<Object> cachePut) {
		super(joinPoint);
		this.cachePut = cachePut;
	}

	/**
	 * 向缓存写入数据
	 * @return Consumer
	 */
	private Consumer<Object> cachePut;

	public Consumer<Object> cachePut() {
		return cachePut;
	}

}
