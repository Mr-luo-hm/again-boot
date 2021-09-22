package com.again.example.netty.net;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author create by 罗英杰 on 2021/9/22
 * @description:
 */
public class VirtualClientMessageHandler extends SimpleChannelInboundHandler<NioSocketChannel> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NioSocketChannel msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channel.writeAndFlush("xxxxxxxxxxxxxxxxxxxxxxxxxx");
        super.channelActive(ctx);
    }
}
