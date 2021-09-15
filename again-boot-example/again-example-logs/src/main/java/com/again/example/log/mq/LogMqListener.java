package com.again.example.log.mq;

import com.again.example.log.elasticsearch.LogSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * @author create by 罗英杰 on 2021/9/15
 * @description:
 */
@Component
@RequiredArgsConstructor
public class LogMqListener {

	private final LogSearchService logSearchService;

	@StreamListener("logexchange")
	public void onMessage(String logJson) {
		try {
			logSearchService.addDoc(logJson);
			System.out.println(logJson);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
