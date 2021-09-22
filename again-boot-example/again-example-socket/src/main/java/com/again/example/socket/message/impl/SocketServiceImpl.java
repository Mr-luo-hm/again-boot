package com.again.example.socket.message.impl;

import com.again.example.socket.entity.SocketEnum;
import com.again.example.socket.entity.SocketPojo;
import com.again.example.socket.lserver.ChannelRepository;
import com.again.example.socket.lserver.ChannelTimeRepository;
import com.again.example.socket.message.SocketService;
import com.again.example.utils.DateTimeUtils;
import com.again.example.utils.JacksonUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author create by 罗英杰 on 2021/9/13
 * @description:
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class SocketServiceImpl implements SocketService {

    private final ChannelRepository channelRepository;

    private final ChannelTimeRepository channelTimeRepository;

    private final static String USER_ID = "userId";

    @Override
    public void addChannel(ChannelHandlerContext ctx, Map json) {
        Integer userId = (Integer) json.get(USER_ID);
        channelRepository.put(userId, ctx.channel());
    }

    @Override
    public void keepAlive(Map map) {
        Integer userId = (Integer) map.get(USER_ID);
        channelTimeRepository.put(userId, DateTimeUtils.get());
    }

    @Override
    public void push(Integer userId) {
        Channel channel = channelRepository.get(userId);
        SocketPojo pojo = new SocketPojo();
        pojo.setType(SocketEnum.RISK_NOTICE.getCode());
        pojo.setMessage(SocketEnum.RISK_NOTICE.getMsg());
        TextWebSocketFrame tws = new TextWebSocketFrame(JacksonUtils.toJson(pojo));
        if (channel != null) {
            channel.writeAndFlush(tws);
        }
    }
}
