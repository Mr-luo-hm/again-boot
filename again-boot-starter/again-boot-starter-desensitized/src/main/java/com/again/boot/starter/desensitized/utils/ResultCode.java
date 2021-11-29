package com.again.boot.starter.desensitized.utils;

/**
 * @author create by 罗英杰 on 2021/11/26
 * @description:
 */
public interface ResultCode {

	/**
	 * 获取业务码
	 * @return 业务码
	 */
	Integer getCode();

	/**
	 * 获取信息
	 * @return 返回结构体中的信息
	 */
	String getMessage();

}
