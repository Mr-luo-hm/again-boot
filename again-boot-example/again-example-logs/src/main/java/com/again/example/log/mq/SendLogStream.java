package com.again.example.log.mq;

import org.springframework.cloud.stream.annotation.Input;

import org.springframework.messaging.MessageChannel;

/**
 * @author create by 罗英杰 on 2021/9/15
 * @description:
 */
public interface SendLogStream {

	@Input("logexchange")
	MessageChannel messageChannel();

}
