package com.again.boot.starter.desensitized.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author create by 罗英杰 on 2021/11/26
 * @description:
 */
@Getter
@AllArgsConstructor
public enum SystemResultCode implements ResultCode {

	// ================ 基础部分，参考 HttpStatus =============
	/**
	 * 成功
	 */
	SUCCESS(200, "Success"),
	/**
	 * 参数错误
	 */
	BAD_REQUEST(400, "Bad Request"),
	/**
	 * 未认证
	 */
	UNAUTHORIZED(401, "Unauthorized"),
	/**
	 * 未授权
	 */
	FORBIDDEN(403, "Forbidden"),
	/**
	 * 服务异常
	 */
	SERVER_ERROR(500, "Internal Server Error");

	private final Integer code;

	private final String message;

}
