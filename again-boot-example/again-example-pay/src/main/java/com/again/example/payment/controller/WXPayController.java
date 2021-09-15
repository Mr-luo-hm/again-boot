package com.again.example.payment.controller;

import com.again.example.payment.utils.PayCommonUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author create by 罗英杰 on 2021/9/15
 * @description:
 */
@RestController
@RequestMapping("/payment")
public class WXPayController {

	/**
	 * 支付 这里 先从前台传
	 * @param orderId 订单 id
	 * @param price 价格
	 * @param body 描述
	 * @return weixin://wxpay/bizpayurl?pr=Rlzae0tzz 微信支付链接
	 * @throws Exception
	 */
	@RequestMapping({ "/pay" })
	public String pay(String orderId, int price, String body) throws Exception {

		return PayCommonUtil.weixinPay(price + "", body, orderId);

	}

}
