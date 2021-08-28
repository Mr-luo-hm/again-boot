package com.again.boot.starter.redis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author create by 罗英杰 on 2021/8/28
 * @description:
 */
@Data
@ConfigurationProperties(prefix = "again.boot.starter.redis")
public class CacheProperties {

	/**
	 * 通用的key前缀
	 */
	private String keyPrefix = "";

	/**
	 * redis锁 后缀
	 */
	private String lockKeySuffix = "locked";

	/**
	 * 默认分隔符
	 */
	private String delimiter = ":";

	/**
	 * 空值标识
	 */
	private String nullValue = "N_V";

	/**
	 * 默认超时时间(s)
	 */
	private long expireTime = 86400L;

	/**
	 * 锁的超时时间(ms)
	 */
	private long lockedTimeOut = 1000L;

}
