package com.again.example.payment.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;

/**
 * @author create by 罗英杰 on 2021/9/15
 * @description:
 */
public class MD5Util {

	/**
	 * 编码,将字节数组转成可识别字符串
	 * @param b
	 * @return
	 */
	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/**
	 * 将自己转成可识别字符串
	 * @param b
	 * @return
	 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n += 256;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return HEX_DIGITS[d1] + HEX_DIGITS[d2];
	}

	/**
	 * 获取指定内容的 MD5值
	 * @param origin 被转换的内容
	 * @param charsetName 字符集
	 * @return
	 */
	public static String MD5Encode(String origin, String charsetName) {
		String resultString = null;
		try {
			resultString = origin;
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charsetName == null || "".equals(charsetName)) {
				resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
			}
			else {
				resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetName)));
			}
		}
		catch (Exception exception) {
		}
		return resultString;
	}

	private static final String HEX_DIGITS[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	public static String UrlEncode(String src) throws UnsupportedEncodingException {
		return URLEncoder.encode(src, "UTF-8").replace("+", "%20");
	}

}
