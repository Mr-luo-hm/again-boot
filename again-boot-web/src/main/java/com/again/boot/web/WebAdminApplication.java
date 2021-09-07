package com.again.boot.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author create by 罗英杰 on 2021/9/6
 * @description:
 */
@SpringBootApplication
@MapperScan({ "com.again.boot.**.mapper" })
@ComponentScan(value = { "com.again.**" })
public class WebAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebAdminApplication.class);
	}

}
