package com.again.boot.starter.redis.operation;

import com.again.boot.starter.redis.config.CachePropertiesHolder;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author create by 罗英杰 on 2021/8/28
 * @description:
 */
public abstract class AbstractCacheOps {

	public AbstractCacheOps(ProceedingJoinPoint joinPoint) {
		this.joinPoint = joinPoint;
	}

	private final ProceedingJoinPoint joinPoint;

	/**
	 * 织入方法
	 * @return ProceedingJoinPoint
	 */
	public ProceedingJoinPoint joinPoint() {
		return joinPoint;
	}

	/**
	 * 检查缓存数据是否是空值
	 * @param cacheData
	 * @return
	 */
	public boolean nullValue(Object cacheData) {
		return CachePropertiesHolder.nullValue().equals(cacheData);
	}

}
