package com.again.example.casual;

import com.again.example.casual.mq.WXPayInputStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @author create by 罗英杰 on 2021/9/10
 * @description:
 */
@SpringBootApplication
@EnableEurekaClient
@EnableBinding({ WXPayInputStream.class })
public class CasualStartApplication {

	public static void main(String[] args) {
		SpringApplication.run(CasualStartApplication.class);
	}

}
