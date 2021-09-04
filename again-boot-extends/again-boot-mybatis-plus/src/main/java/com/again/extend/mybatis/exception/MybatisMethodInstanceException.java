package com.again.extend.mybatis.exception;

/**
 * @author create by 罗英杰 on 2021/9/4
 * @description: mybatis plus 方法 实例化获取异常
 */
public class MybatisMethodInstanceException extends RuntimeException {

	public MybatisMethodInstanceException(String message, Throwable cause) {
		super(message, cause);
	}

}
