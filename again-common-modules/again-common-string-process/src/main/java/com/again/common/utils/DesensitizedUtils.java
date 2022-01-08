package com.again.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author create by 罗英杰 on 2021/11/29
 * @description:
 */
public class DesensitizedUtils {

	public static String desensitizedPhoneNumber(String phoneNumber) {
		return phoneNumber.replaceAll("(\\w{3})\\w*(\\w{4})", "$1****$2");
	}

	public static String chineseNameDesensitization(String fullName) {
		String name = StringUtils.left(fullName, 1);
		return StringUtils.rightPad(name, StringUtils.length(fullName), "*");
	}

	public static String desensitizedIdCard(String idNumber) {
		if (idNumber.length() == 15) {
			idNumber = idNumber.replaceAll("(\\w{6})\\w*(\\w{3})", "$1******$2");
		}
		if (idNumber.length() == 18) {
			idNumber = idNumber.replaceAll("(\\w{6})\\w*(\\w{3})", "$1*********$2");
		}
		return idNumber;
	}

	public static String emailDesensitization(String email) {
		// 电子邮箱隐藏@前面的3个字符
		if (StringUtils.isEmpty(email)) {
			return email;
		}
		String encrypt = email.replaceAll("(\\w+)\\w{3}@(\\w+)", "$1***@$2");
		if (email.equalsIgnoreCase(encrypt)) {
			encrypt = email.replaceAll("(\\w*)\\w{1}@(\\w+)", "$1*@$2");
		}
		return encrypt;
	}

	/**
	 * 银行账号脱敏
	 * @param acctNo
	 * @return
	 */
	public static String acctNoDesensitization(String acctNo) {
		// 银行账号保留前六后四
		if (StringUtils.isNotEmpty(acctNo)) {
			String regex = "(\\w{6})(.*)(\\w{4})";
			Matcher m = Pattern.compile(regex).matcher(acctNo);
			if (m.find()) {
				String rep = m.group(2);
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < rep.length(); i++) {
					sb.append("*");
				}
				acctNo = acctNo.replaceAll(rep, sb.toString());
			}
		}
		return acctNo;
	}

	public static String desensitizedAddress(String address) {
		return StringUtils.left(address, 3).concat(StringUtils.removeStart(StringUtils
				.leftPad(StringUtils.right(address, address.length() - 11), StringUtils.length(address), "*"), "***"));
	}

}
