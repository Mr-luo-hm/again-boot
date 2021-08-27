package com.again.boot.starter.storage.config;

import com.again.boot.starter.storage.client.ALiYunClient;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author create by 罗英杰 on 2021/8/27
 * @description:
 */
@AllArgsConstructor
@EnableConfigurationProperties(FileStorageProperties.class)
public class FileStorageAutoConfiguration {

	private final FileStorageProperties properties;

	@Bean
	@ConditionalOnMissingBean(FileStorageClient.class)
	@ConditionalOnProperty(name = "file.storage.type", havingValue = "ALIYUN")
	FileStorageClient aliyunOssClient() {
		return new ALiYunClient(properties.getEndpoint(), properties.getAccessKey(), properties.getAccessSecret(),
				properties.getBucketName());
	}

}
