package com.again.example.gateway.fallback;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author create by 罗英杰 on 2021/9/10
 * @description: 熔断降级
 */
@RestController
public class FallbackController {

	@GetMapping("/fallbackA")
	public String fallbackA() {
		return "服务暂时不可用";
	}

}
