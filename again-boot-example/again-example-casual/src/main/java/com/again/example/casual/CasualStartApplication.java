package com.again.example.casual;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author create by 罗英杰 on 2021/9/10
 * @description:
 */
@SpringBootApplication
@EnableEurekaClient
public class CasualStartApplication {

	public static void main(String[] args) {
		SpringApplication.run(CasualStartApplication.class);
	}

}
