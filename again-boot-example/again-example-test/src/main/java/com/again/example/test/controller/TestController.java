package com.again.example.test.controller;

import com.again.common.annotation.RateLimit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author create by 罗英杰 on 2022/1/8
 * @description:
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

	@GetMapping("/list")
	@RateLimit(rate = 10000, rateInterval = 1, key = "曲奇妙妙屋")
	public String list() {
		return "ssss";
	}

}
