package com.again.boot.starter.redis.serialize;

import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.nio.charset.StandardCharsets;

/**
 * @author create by 罗英杰 on 2021/8/28
 * @description:
 */
public class PrefixStringRedisSerializer extends StringRedisSerializer {

	private final String prefix;

	private final boolean enable;

	public PrefixStringRedisSerializer(String prefix) {
		super(StandardCharsets.UTF_8);
		this.prefix = prefix;
		this.enable = prefix != null && !"".equals(prefix);
	}

	@Override
	public String deserialize(byte[] bytes) {
		String originKey = super.deserialize(bytes);
		// 如果有全局前缀，则需要删除
		if (enable && originKey != null && originKey.startsWith(prefix)) {
			originKey = originKey.substring(prefix.length());
		}
		return originKey;
	}

	@Override
	public byte[] serialize(String string) {
		if (enable && string != null) {
			string = prefix + string;
		}
		return super.serialize(string);
	}

}
