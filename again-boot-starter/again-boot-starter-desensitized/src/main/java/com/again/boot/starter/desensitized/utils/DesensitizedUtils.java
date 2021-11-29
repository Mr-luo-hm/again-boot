package com.again.boot.starter.desensitized.utils;

/**
 * @author create by 罗英杰 on 2021/11/26
 * @description:
 */
public class DesensitizedUtils {

	public static String desensitizedPhoneNumber(String phoneNumber) {
		return phoneNumber.replaceAll("(\\w{3})\\w*(\\w{4})", "$1****$2");
	}

}
