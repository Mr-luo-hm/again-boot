package com.again.example.log.elasticsearch.impl;

import com.again.example.log.elasticsearch.LogSearchService;
import com.again.example.utils.JacksonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.rollover.RolloverRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.ReindexRequest;
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
	@Override
	public void updateIndexReindexWhatsFriend(String oldIndex, String newIndex, Integer size) throws IOException {
		// 7.x
//		ReindexRequest request = new ReindexRequest();
//		request.setSlices(5);// 当slices的数量等于索引中的分片数量时，查询性能最高效 slices大小大于分片数，非但不会提升效率，反而会增加开销
//		request.setSourceBatchSize(size);
//		request.setSourceIndices(oldIndex);
//		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
//		request.setSourceQuery(queryBuilder);
//		request.setDestIndex(newIndex);
//		TaskSubmissionResponse task = restHighLevelClient.submitReindexTask(request, RequestOptions.DEFAULT);
//		System.out.println(task.getTask());
	}
	@Override
	public void addIndexWhatsFriendAlias(String index) throws IOException {
		// 创建索引
		CreateIndexRequest request = new CreateIndexRequest(index);
		// 2、设置索引的settings
		request.settings(
				Settings.builder().put("index.number_of_shards", 3).put("index.number_of_replicas", 1).build());
		// 3、设置索引的mapping 默认文档类型_doc
		request.mapping("", XContentType.JSON);
		Alias alias = new Alias("W_F_INDEX");
		alias.writeIndex(true);
		request.alias(alias);
		restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
	}

	@Override
	public void rolloverWhatsFriend() throws IOException {
		RolloverRequest rolloverIndex = new RolloverRequest("W_F_INDEX", null);
		// 3000w 切换 或者18g 测试动态调整 数量减少
		rolloverIndex.addMaxIndexDocsCondition(30000000);
		rolloverIndex.addMaxIndexSizeCondition(new ByteSizeValue(19327352832L));
		CreateIndexRequest createIndexRequest = rolloverIndex.getCreateIndexRequest();
		createIndexRequest.settings(
				Settings.builder().put("index.number_of_shards", 3).put("index.number_of_replicas", 1).build());
		createIndexRequest.mapping("", XContentType.JSON);
		restHighLevelClient.indices().rollover(rolloverIndex, RequestOptions.DEFAULT);
	}
}
