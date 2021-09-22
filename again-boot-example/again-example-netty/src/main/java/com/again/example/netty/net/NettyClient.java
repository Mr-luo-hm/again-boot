package com.again.example.netty.net;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.ResourceLeakDetector;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author create by 罗英杰 on 2021/9/22
 * @description:
 */
@Slf4j
@Component
@Data

public class NettyClient {
    public final static int serverPort = 20005;

    public final static String serverIp = "127.0.0.1";

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventExecutors).channel(NioSocketChannel.class).option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new NioEventLoopGroup(5), "localMessageClientHandler", new VirtualClientMessageHandler());
                        System.out.println("NettyClient starter...");
                    }
                });
        bootstrap.connect(serverIp, serverPort).sync();

    }
}
