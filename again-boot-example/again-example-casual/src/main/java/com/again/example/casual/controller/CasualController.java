package com.again.example.casual.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author create by 罗英杰 on 2021/9/10
 * @description:
 */
@RequestMapping("foo")
@RestController
public class CasualController {

	@GetMapping("/api")
	public String casual() {
		// throw new RuntimeException("xxxxxxxxx");
		return "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	}

}
