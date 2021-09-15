package com.again.example.gateway.mq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author create by 罗英杰 on 2021/9/15
 * @description:
 */
public interface SendLogStream {

	/**
	 * 日志
	 * @return
	 */
	@Output("logexchange")
	MessageChannel messageChannel();

}
