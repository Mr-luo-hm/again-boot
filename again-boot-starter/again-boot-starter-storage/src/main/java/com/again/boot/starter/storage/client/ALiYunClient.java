package com.again.boot.starter.storage.client;

import com.again.boot.starter.storage.config.FileStorageClient;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author create by 罗英杰 on 2021/8/27
 * @description:
 */
@RequiredArgsConstructor
public class ALiYunClient implements FileStorageClient, InitializingBean, DisposableBean {

	private final String endpoint;

	private final String accessKey;

	private final String accessSecret;

	private final String bucketName;

	private OSS client;

	private static String HEADER_NO_CACHE = "no-cache";

	private final static ByteArrayInputStream FOLDER_CONTENT = new ByteArrayInputStream(new byte[0]);

	@Override
	public String putObject(String objectName, InputStream inputStream) {
		client.putObject(bucketName, objectName, inputStream);
		return objectName;
	}

	@Override
	public void deleteObject(String objectName) {
		if (client.doesObjectExist(bucketName, objectName)) {
			client.deleteObject(bucketName, objectName);
		}
	}

	@Override
	public void copyObject(String sourceObjectName, String targetObjectName) {
		client.copyObject(bucketName, sourceObjectName, bucketName, targetObjectName);
	}

	@Override
	public void createFolder(String folderName) {
		if (!client.doesObjectExist(bucketName, folderName)) {
			client.putObject(bucketName, folderName, FOLDER_CONTENT);
		}
	}

	@Override
	public void destroy() {
		if (this.client != null) {
			this.client.shutdown();
		}
	}

	@Override
	public void afterPropertiesSet() {
		Assert.hasText(endpoint, "endpoint 为空");
		Assert.hasText(accessKey, "Oss accessKey为空");
		Assert.hasText(accessSecret, "Oss accessSecret为空");
		client = new OSSClientBuilder().build(endpoint, accessKey, accessSecret);
	}

}
