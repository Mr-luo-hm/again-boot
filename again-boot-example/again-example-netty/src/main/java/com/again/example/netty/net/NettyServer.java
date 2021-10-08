package com.again.example.netty.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author create by 罗英杰 on 2021/9/22
 * @description:
 */
@Slf4j
public class NettyServer {

	public static void main(String[] args) throws InterruptedException {
		NioEventLoopGroup eventExecutors = new NioEventLoopGroup();

		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.group(eventExecutors, new NioEventLoopGroup(5)).channel(NioServerSocketChannel.class)
				.handler(new LoggingHandler(LogLevel.INFO)).childHandler(new ChannelInitializer<NioSocketChannel>() {
					@Override
					protected void initChannel(NioSocketChannel ch) throws Exception {
						log.info("init...");
					}
				});

		ChannelFuture channelFuture = bootstrap.bind(20005).sync();
		channelFuture.channel().writeAndFlush("xxxxxxxxxxxxxxxx");
		log.info("proxy Net Server Started!");
	}

}
