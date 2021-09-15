package com.again.example.beans;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author create by 罗英杰 on 2021/9/15
 * @description:
 */
@Data
public class LogBean {

	/**
	 * 请求url
	 */
	private String url;

	/**
	 * 代表是哪个网关处理的请求
	 */
	private String serverIp;

	/**
	 * path路径
	 */
	private String path;

	/**
	 * 代表发起请求的客户端的ip
	 */
	private String remoteIp;

	/**
	 * 请求的服务是是哪个
	 */
	private String apiName;

	/**
	 * 总响应时间,从接受到请求到最后返回结果总共多少时间
	 */
	private long platformRepTime;

	/**
	 * 请求传递的参数
	 */
	private String requestContent;

	/**
	 * 收到请求的时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime receiveTime;

	/**
	 * 错误码
	 */
	private int errorCode;

	private String method;

}
