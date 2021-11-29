package com.again.boot.starter.pv.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author create by 罗英杰 on 2021/11/24
 * @description:
 */
public interface PvLogHandler<T> {

	/**
	 * 记录日志
	 * @param request 请求信息
	 * @param response 响应信息
	 */
	default void logRecord(HttpServletRequest request, HttpServletResponse response) {
		T log = prodLog(request, response);
		saveLog(log);
	}

	/**
	 * 生产一个日志
	 * @return accessLog
	 * @param request 请求信息
	 * @param response 响应信息
	 */
	T prodLog(HttpServletRequest request, HttpServletResponse response);

	/**
	 * 保存日志 落库/或输出到文件等
	 * @param accessLog 访问日志
	 */
	void saveLog(T accessLog);

}
