package com.again.common.handler;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author create by 罗英杰 on 2022/1/8
 * @description:
 */
public class SimpleRateLimiter {

	private final StringRedisTemplate stringRedisTemplate;

	public SimpleRateLimiter(StringRedisTemplate stringRedisTemplate) {
		this.stringRedisTemplate = stringRedisTemplate;
	}

	/**
	 * @param user 用户
	 * @param action 操作行为
	 * @param period 指定时间
	 * @param maxCount 次数
	 * @return
	 */
	public boolean isActionAllowed(String user, String action, int period, int maxCount) {
		String key = String.format("hits:%s:%s", user, action);
		Long nowTs = System.currentTimeMillis();
		Long execute = stringRedisTemplate.execute((RedisCallback<Long>) connection -> {
			connection.multi();
			connection.zAdd(key.getBytes(), nowTs, ("" + nowTs).getBytes());
			connection.zRangeByScoreWithScores(key.getBytes(), 0, nowTs - period * 1000);
			connection.expire(key.getBytes(), period);
			connection.exec();
			connection.close();
			return connection.zCard(key.getBytes());
		});
		return execute < maxCount;
	}

}
