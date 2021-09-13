package com.again.example.socket.entity;

import lombok.Getter;

/**
 * @author create by 罗英杰 on 2021/9/13
 * @description:
 */
@Getter
public enum SocketEnum {

	// 心跳通知
	KEEP_ALIVE(999, "pong"),
	// 风控通知
	RISK_NOTICE(1000, "风控通知");

	int code;

	String msg;

	SocketEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

}
