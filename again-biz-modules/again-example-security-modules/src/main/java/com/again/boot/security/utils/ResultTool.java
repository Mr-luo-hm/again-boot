package com.again.boot.security.utils;

/**
 * @author create by 罗英杰 on 2021/9/6
 * @description:
 */
public class ResultTool {

	public static JsonResult success() {
		return new JsonResult(true);
	}

	public static <T> JsonResult<T> success(T data) {
		return new JsonResult(true, data);
	}

	public static JsonResult fail() {
		return new JsonResult(false);
	}

	public static JsonResult fail(ResultCode resultEnum) {
		return new JsonResult(false, resultEnum);
	}

}
