package com.again.common.weblog.utils;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author create by 罗英杰 on 2021/12/31
 * @description:
 */
@Slf4j
public class HttpUtils {

	private static final String UNKNOWN = "unknown";

	/**
	 * 获取ip地址
	 */
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		String comma = ",";
		String localhost = "127.0.0.1";
		if (ip.contains(comma)) {
			ip = ip.split(",")[0];
		}
		if (localhost.equals(ip)) {
			// 获取本机真正的ip地址
			try {
				ip = InetAddress.getLocalHost().getHostAddress();
			}
			catch (UnknownHostException e) {
				log.error(e.getMessage(), e);
			}
		}
		return ip;
	}

}
