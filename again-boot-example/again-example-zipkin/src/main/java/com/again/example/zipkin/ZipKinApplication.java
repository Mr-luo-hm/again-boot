package com.again.example.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin2.server.internal.EnableZipkinServer;

/**
 * @author create by 罗英杰 on 2021/10/8
 * @description:
 */
@SpringBootApplication
@EnableZipkinServer // 开启 zipkin server
public class ZipKinApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipKinApplication.class, args);
	}

}
