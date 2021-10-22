package com.again.example.casual;

import com.again.example.casual.mq.WXPayInputStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author create by 罗英杰 on 2021/9/10
 * @description:
 */
@SpringBootApplication
@EnableEurekaClient
@EnableBinding({ WXPayInputStream.class })
@RibbonClient("again-example-casual")
public class CasualStartApplication {

	public static void main(String[] args) {
		SpringApplication.run(CasualStartApplication.class);
	}

	@Bean
	@LoadBalanced // 给当前对象开启负载均衡,具体是在调用@RibbonClient("04provider-eureka")这个配置设置的服务的时候开启
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
