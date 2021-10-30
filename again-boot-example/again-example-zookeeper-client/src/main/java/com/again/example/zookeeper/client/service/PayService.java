package com.again.example.zookeeper.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author create by 罗英杰 on 2021/10/30
 * @description:
 */
@Service
@FeignClient("again-example-zookeeper")
public interface PayService {

	@GetMapping("/payment/zk")
	String getList();

}
