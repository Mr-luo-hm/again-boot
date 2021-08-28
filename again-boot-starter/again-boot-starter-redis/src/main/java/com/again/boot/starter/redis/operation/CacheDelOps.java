package com.again.boot.starter.redis.operation;

import com.again.boot.starter.redis.operation.function.VoidMethod;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author create by 罗英杰 on 2021/8/28
 * @description:
 */
public class CacheDelOps extends AbstractCacheOps {

	/**
	 * 删除缓存数据
	 * @return VoidMethod
	 */
	private VoidMethod cacheDel;

	public CacheDelOps(ProceedingJoinPoint joinPoint, VoidMethod cacheDel) {
		super(joinPoint);
		this.cacheDel = cacheDel;
	}

	public VoidMethod cacheDel() {
		return cacheDel;
	}

}
