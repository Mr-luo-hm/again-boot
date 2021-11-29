package com.again.boot.starter.desensitized.enumerate;

/**
 * @author create by 罗英杰 on 2021/11/26
 * @description:
 */
public enum SensitiveTypeEnum {

	/**
	 * 中文名
	 */
	CHINESE_NAME,
	/**
	 * 身份证号
	 */
	ID_CARD,
	/**
	 * 座机号
	 */
	FIXED_PHONE,
	/**
	 * 手机号
	 */
	MOBILE_PHONE,
	/**
	 * 地址
	 */
	ADDRESS,
	/**
	 * 电子邮件
	 */
	EMAIL,
	/**
	 * 银行卡
	 */
	BANK_CARD,
	/**
	 * 密码
	 */
	PASSWORD,
	/**
	 * 车牌号
	 */
	CARNUMBER,
	/**
	 * 不需要脱敏 或者方法注册
	 */
	NO;

}
