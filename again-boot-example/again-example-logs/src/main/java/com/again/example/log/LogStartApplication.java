package com.again.example.log;

import com.again.example.log.mq.SendLogStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @author create by 罗英杰 on 2021/9/15
 * @description:
 */
@SpringBootApplication
@EnableEurekaClient
@EnableBinding(SendLogStream.class)
public class LogStartApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogStartApplication.class);
	}

}
