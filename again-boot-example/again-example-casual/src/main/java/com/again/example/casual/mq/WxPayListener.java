package com.again.example.casual.mq;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * @author create by 罗英杰 on 2021/9/15
 * @description:
 */
@Component
public class WxPayListener {

	@StreamListener("paysuccessexchange")
	public void onMessage(String oid) {
		System.out.println(oid);
		// 业务处理
	}

}
