package com.again.example.config.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author create by 罗英杰 on 2021/10/30
 * @description:
 */
@RestController
public class TestController {

	@Autowired
	AuthServiceImpl authService;

	@PostMapping("/login")
	public String test(String username, String password) {
		String login = authService.login(username, password);
		return login;
	}

	@PostMapping("/logout2")
	public String test() {

		return "ok";
	}

}
