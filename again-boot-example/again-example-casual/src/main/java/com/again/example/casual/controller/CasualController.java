package com.again.example.casual.controller;

import com.again.boot.starter.desensitized.annotation.Desensitized;
import com.again.boot.starter.desensitized.enumerate.SensitiveTypeEnum;
import com.again.boot.starter.desensitized.utils.R;
import com.again.example.beans.LogBean;
import com.again.extend.wipe.data.annotation.WipeData;
import com.again.extend.wipe.data.constant.Constants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
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
	@WipeData(value = Constants.PHONE_NO)
	public List response() {
		ArrayList<LogBean> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			LogBean logBean = new LogBean();
			logBean.setId(i);
			logBean.setMethod("1258693691" + i);
			/*
			 * HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
			 *
			 * ArrayList<LogBean> logBeans = new ArrayList<>(); for (int i1 = 0; i1 < 3;
			 * i1++) { LogBean bean = new LogBean(); bean.setId(i1); bean.setMethod("菜单" +
			 * i1); logBeans.add(bean); } logBean.setBeans(logBeans);
			 */
			list.add(logBean);

		}
		return list;
	}

	@GetMapping("/menu2")
	@Desensitized(targetClass = LogBean.class)
	public R<?> test() {
		ArrayList<LogBean> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			LogBean logBean = new LogBean();
			logBean.setId(i);
			logBean.setMethod("1258693691" + i);
			logBean.setPhoneNumber("18730334344");
			/*
			 * HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
			 *
			 * ArrayList<LogBean> logBeans = new ArrayList<>(); for (int i1 = 0; i1 < 3;
			 * i1++) { LogBean bean = new LogBean(); bean.setId(i1); bean.setMethod("菜单" +
			 * i1); logBeans.add(bean); } logBean.setBeans(logBeans);
			 */
			list.add(logBean);

		}
		return R.ok(list);
	}

	@GetMapping("/menu3")
	@Desensitized(targetClass = LogBean.class)
	public HashMap tes3t() {
		HashMap<Object, Object> map = new HashMap<>();
		LogBean logBean = new LogBean();
		logBean.setMethod("1258693691");
		logBean.setPhoneNumber("18730334344");
		map.put(" x", logBean);
		return map;
	}

}
