package com.again.example.casual.mq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author create by 罗英杰 on 2021/9/15
 * @description:
 */
public interface WXPayInputStream {

	@Input("paysuccessexchange")
	SubscribableChannel subscribable_channel();

}
