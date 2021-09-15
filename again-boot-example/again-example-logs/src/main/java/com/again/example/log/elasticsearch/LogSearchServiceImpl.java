package com.again.example.log.elasticsearch;

import com.again.example.utils.JacksonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author create by 罗英杰 on 2021/9/15
 * @description:
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class LogSearchServiceImpl implements LogSearchService {

	private final RestHighLevelClient restHighLevelClient;

	@Override
	public void addDoc(String json) {
		try {
			IndexRequest indexRequest = new IndexRequest("again_log", "_doc");
			indexRequest.source(json, XContentType.JSON);
			IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
			log.debug("add: " + JacksonUtils.toJson(indexResponse));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
