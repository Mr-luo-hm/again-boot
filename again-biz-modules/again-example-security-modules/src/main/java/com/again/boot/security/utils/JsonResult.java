package com.again.boot.security.utils;

/**
 * @author create by 罗英杰 on 2021/9/6
 * @description: 统一json返回体
 */

import lombok.Data;

import java.io.Serializable;

@Data
public class JsonResult<T> implements Serializable {

	private Boolean success;

	private Integer errorCode;

	private String errorMsg;

	private T data;

	public JsonResult(boolean success) {
		this.success = success;
		this.errorCode = success ? ResultCode.SUCCESS.getCode() : ResultCode.COMMON_FAIL.getCode();
		this.errorMsg = success ? ResultCode.SUCCESS.getMessage() : ResultCode.COMMON_FAIL.getMessage();
	}

	public JsonResult(boolean success, ResultCode resultEnum) {
		this.success = success;
		this.errorCode = success ? ResultCode.SUCCESS.getCode()
				: (resultEnum == null ? ResultCode.COMMON_FAIL.getCode() : resultEnum.getCode());
		this.errorMsg = success ? ResultCode.SUCCESS.getMessage()
				: (resultEnum == null ? ResultCode.COMMON_FAIL.getMessage() : resultEnum.getMessage());
	}

	public JsonResult(boolean success, T data) {
		this.success = success;
		this.errorCode = success ? ResultCode.SUCCESS.getCode() : ResultCode.COMMON_FAIL.getCode();
		this.errorMsg = success ? ResultCode.SUCCESS.getMessage() : ResultCode.COMMON_FAIL.getMessage();
		this.data = data;
	}

	public JsonResult(boolean success, ResultCode resultEnum, T data) {
		this.success = success;
		this.errorCode = success ? ResultCode.SUCCESS.getCode()
				: (resultEnum == null ? ResultCode.COMMON_FAIL.getCode() : resultEnum.getCode());
		this.errorMsg = success ? ResultCode.SUCCESS.getMessage()
				: (resultEnum == null ? ResultCode.COMMON_FAIL.getMessage() : resultEnum.getMessage());
		this.data = data;
	}

}
