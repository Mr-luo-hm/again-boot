package com.again.example.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author create by 罗英杰 on 2021/9/9
 * @description:
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaStartApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaStartApplication.class, args);
	}

}
