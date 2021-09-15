package com.again.example.payment;

import com.again.example.payment.mq.WXPayOutStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @author create by 罗英杰 on 2021/9/15
 * @description:
 */
@SpringBootApplication
@EnableBinding(WXPayOutStream.class)
public class PayStartApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayStartApplication.class, args);
	}

}
