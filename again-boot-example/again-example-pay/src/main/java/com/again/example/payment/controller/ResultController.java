package com.again.example.payment.controller;

import com.again.example.payment.mq.WXPayOutStream;
import com.again.example.payment.utils.PayCommonUtil;
import com.again.example.payment.utils.PayConfigUtil;
import com.again.example.payment.utils.XMLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeMap;

/**
 * @author create by 罗英杰 on 2021/9/15
 * @description:
 */
@RequestMapping("/payresult")
@RestController
public class ResultController {

	@Autowired
	private WXPayOutStream wxPayOutStream;

	@RequestMapping("/result")
	public String notifyResult(HttpServletRequest request) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String line;
			StringBuilder stringBuffer = new StringBuilder();
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
			}
			// xml字符串
			String result = stringBuffer.toString();
			System.err.println("微信支付结果:" + result);
			TreeMap treeMap = new TreeMap<>(XMLUtil.doXMLParse(result));
			if (PayCommonUtil.isTenpaySign("utf-8", treeMap, PayConfigUtil.API_KEY)) {
				System.err.println("校验没有错误.是微信告诉我们的结果");
				// 通知所有需要知道支付结果的地方
				MessageChannel messageChannel = wxPayOutStream.messageChannel();
				messageChannel.send(new GenericMessage<>(treeMap.get("out_trade_no").toString()));
			}

			return "<xml>\n" + "  <return_code><![CDATA[SUCCESS]]></return_code>\n"
					+ "  <return_msg><![CDATA[OK]]></return_msg>\n" + "</xml>";
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return "<xml>\n" + "  <return_code><![CDATA[FAIL]]></return_code>\n"
				+ "  <return_msg><![CDATA[OK]]></return_msg>\n" + "</xml>";
	}

}
