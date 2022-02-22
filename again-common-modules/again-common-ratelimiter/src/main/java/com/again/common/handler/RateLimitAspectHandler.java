package com.again.common.handler;

import com.again.common.annotation.RateLimit;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author create by 罗英杰 on 2022/1/8
 * @description:
 */
@Aspect
@Component
@Order(0)
@Slf4j
public class RateLimitAspectHandler {

	private final SimpleRateLimiter rateLimiterService;

	public RateLimitAspectHandler(SimpleRateLimiter rateLimiterService) {
		this.rateLimiterService = rateLimiterService;
	}

	@Around(value = "@annotation(rateLimit)")
	public Object around(ProceedingJoinPoint joinPoint, RateLimit rateLimit) throws Throwable {
		boolean actionAllowed = rateLimiterService.isActionAllowed(rateLimit.key(), rateLimit.fallbackFunction(),
				rateLimit.rate(), rateLimit.rateInterval());
		if (!actionAllowed) {
			return "当前请求繁忙";
		}
		return joinPoint.proceed();
	}

}
