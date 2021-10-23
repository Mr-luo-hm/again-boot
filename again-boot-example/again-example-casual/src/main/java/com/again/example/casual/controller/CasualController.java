package com.again.example.casual.controller;

import com.again.example.beans.LogBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.List;

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

	@GetMapping("/menu")
	public List response(){
		ArrayList<LogBean> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			LogBean logBean = new LogBean();
			logBean.setId(i);
			logBean.setMethod("菜单"+i);
			ArrayList<LogBean> logBeans = new ArrayList<>();
			for (int i1 = 0; i1 < 3; i1++) {
				LogBean bean = new LogBean();
				bean.setId(i1);
				bean.setMethod("菜单"+i1);
				logBeans.add(bean);
			}
			logBean.setBeans(logBeans);
			list.add(logBean);

		}
		return list;
	}
}
