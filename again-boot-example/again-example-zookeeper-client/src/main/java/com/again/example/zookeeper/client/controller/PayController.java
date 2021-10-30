package com.again.example.zookeeper.client.controller;

import com.again.example.zookeeper.client.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author create by 罗英杰 on 2021/10/30
 * @description:
 */
@RestController
public class PayController {

	@Resource
	private RestTemplate restTemplate;

	@Autowired
	private PayService payService;

	private static final String url = "http://again-example-zookeeper";

	@GetMapping("/client")
	public String payment() {
		return payService.getList();
	}

	@GetMapping("/client2")
	public String payment2() {
		return restTemplate.getForObject(url + "/payment/zk", String.class);
	}

}
