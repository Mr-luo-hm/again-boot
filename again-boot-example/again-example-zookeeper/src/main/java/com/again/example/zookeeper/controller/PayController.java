package com.again.example.zookeeper.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author create by 罗英杰 on 2021/10/30
 * @description:
 */
@RestController
public class PayController {

	@GetMapping("/payment/zk")
	public String payment() {
		return "ok";
	}

}
