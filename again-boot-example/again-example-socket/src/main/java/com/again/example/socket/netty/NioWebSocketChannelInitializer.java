package com.again.example.socket.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author create by 罗英杰 on 2021/9/13
 * @description:
 */
@Component
public class NioWebSocketChannelInitializer extends ChannelInitializer<NioSocketChannel> {

	@Override
	protected void initChannel(NioSocketChannel ch) {
		ch.pipeline().addLast(new IdleStateHandler(60, 0, 0, TimeUnit.SECONDS));
		// 设置log监听器，并且日志级别为debug，方便观察运行流程
		ch.pipeline().addLast("logging", new LoggingHandler("DEBUG"));
		// 设置解码器
		ch.pipeline().addLast("http-codec", new HttpServerCodec());
		// 聚合器，使用websocket会用到
		ch.pipeline().addLast("aggregator", new HttpObjectAggregator(65536));
		// 用于大数据的分区传输
		ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
		// 自定义的业务handler
		ch.pipeline().addLast("messageServerHandler", new NioWebSocketHandler());
	}

}
