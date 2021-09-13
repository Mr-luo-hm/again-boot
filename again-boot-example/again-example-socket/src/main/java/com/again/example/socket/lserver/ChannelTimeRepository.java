package com.again.example.socket.lserver;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author create by 罗英杰 on 2021/9/13
 * @description:
 */
@Component
@SuppressWarnings("ALL")
public class ChannelTimeRepository {

	/**
	 * key 为 userid channel 为对应的更新时间
	 */
	private final static Map<Integer, LocalDateTime> channels = new ConcurrentHashMap<>();

	public void put(Integer key, LocalDateTime value) {
		channels.put(key, value);
	}

	public LocalDateTime get(Integer key) {
		return channels.get(key);
	}

	public void remove(Integer key) {
		channels.remove(key);
	}

	public int size() {
		return channels.size();
	}

}
