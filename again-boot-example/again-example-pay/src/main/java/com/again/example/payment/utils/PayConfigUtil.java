package com.again.example.payment.utils;

/**
 * @author create by 罗英杰 on 2021/9/15
 * @description:
 */
public class PayConfigUtil {

	/**
	 * 微信公众号 id
	 */
	public static String APP_ID = "";

	/**
	 * 商户 id
	 */
	public static String MCH_ID = "";

	/**
	 * API_KEY 用于生成签名
	 */
	public static String API_KEY = "";

	/**
	 * 微信统一下单地址
	 */
	public static String UFDOOER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

	/**
	 * 回调地址,用于接收微信告诉我们的支付结果
	 */
	public static String NOTIFY_URL = "http://tomcat01.qfjava.cn:81/paycenter/payresult/result";

	/**
	 * 发起请求的地址,可以写我们的服务器地址,也可以传递客户的 ip
	 */
	public static String CREATE_IP = "114.242.26.51";

}
