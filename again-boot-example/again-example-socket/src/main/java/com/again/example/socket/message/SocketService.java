package com.again.example.socket.message;

import io.netty.channel.ChannelHandlerContext;

import java.util.Map;

/**
 * @author create by 罗英杰 on 2021/9/13
 * @description:
 */
public interface SocketService {

	/**
	 * 存放 channel
	 * @param ctx 新增连接购的 上下文
	 * @param json 建立连接之后的 前端解析后的map
	 */
	void addChannel(ChannelHandlerContext ctx, Map json);

	/**
	 * 保活 心跳
	 * @param map 前端传过来解析后的数据map
	 */
	void keepAlive(Map map);

	/**
	 * 根据用户 id 进行推送 风控通知
	 * @param userId userId
	 */
	void push(Integer userId);

}
