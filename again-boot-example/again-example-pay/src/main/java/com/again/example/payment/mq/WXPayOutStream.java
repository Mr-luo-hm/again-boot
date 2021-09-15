package com.again.example.payment.mq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author create by 罗英杰 on 2021/9/15
 * @description:
 */
public interface WXPayOutStream {

	/**
	 * 支付成功后
	 * @return
	 */
	@Output("paysuccessexchange")
	MessageChannel messageChannel();

}
