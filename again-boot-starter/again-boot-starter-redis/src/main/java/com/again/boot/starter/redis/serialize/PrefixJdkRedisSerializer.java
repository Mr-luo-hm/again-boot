package com.again.boot.starter.redis.serialize;

import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

/**
 * @author create by 罗英杰 on 2021/8/28
 * @description: 自定义Key序列化工具，添加全局key前缀
 */
public class PrefixJdkRedisSerializer extends JdkSerializationRedisSerializer {

	private final String prefix;

	private final boolean enable;

	public PrefixJdkRedisSerializer(String prefix) {
		this.prefix = prefix;
		this.enable = prefix != null && !"".equals(prefix);
	}

	@Override
	public Object deserialize(byte[] bytes) {
		Object origin = super.deserialize(bytes);
		if (enable && origin instanceof String) {
			String originKey = (String) origin;
			// 如果有全局前缀，则需要删除
			if (originKey.startsWith(prefix)) {
				originKey = originKey.substring(prefix.length());
			}
			return originKey;
		}
		else {
			return origin;
		}
	}

	@Override
	public byte[] serialize(Object object) {
		if (enable && object instanceof String) {
			String key = prefix + object;
			return super.serialize(key);
		}
		else {
			return super.serialize(object);
		}
	}

}
