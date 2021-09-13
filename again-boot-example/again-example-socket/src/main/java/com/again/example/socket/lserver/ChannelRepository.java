package com.again.example.socket.lserver;

import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author create by 罗英杰 on 2021/9/13
 * @description:
 */
@SuppressWarnings("ALL")
@Component
public class ChannelRepository {

	/**
	 * key 为 userid channel 为对应的channel
	 */
	private final static Map<Integer, Channel> channels = new ConcurrentHashMap<>();

	public void put(Integer key, Channel value) {
		channels.put(key, value);
	}

	public Channel get(Integer key) {
		return channels.get(key);
	}

	public void remove(Integer key) {
		channels.remove(key);
	}

	public int size() {
		return channels.size();
	}

	public List<Integer> getAllKey() {
		ArrayList<Integer> list = new ArrayList<>();
		channels.forEach((k, v) -> {
			list.add(k);
		});
		return list;
	}

}
