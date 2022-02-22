package com.again.common.weblog.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author create by 罗英杰 on 2021/12/31
 * @description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebLog {

	private String threadId;

	private String threadName;

	private String ip;

	private String url;

	private String httpMethod;

	private String classMethod;

	private Object requestParams;

	private Object result;

	private Long timeCost;

	private String os;

	private String browser;

	private String userAgent;

}
