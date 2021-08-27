package com.again.boot.starter.storage.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author create by 罗英杰 on 2021/8/27
 * @description:
 */
@Data
@ConfigurationProperties(prefix = "file.storage")
public class FileStorageProperties {

	/**
	 * 使用的文件存储服务类型
	 */
	private FileStorageTypeEnum type;

	/**
	 * endpoint 服务地址 eg. http://oss-cn-qingdao.aliyuncs.com
	 */
	private String endpoint;

	/**
	 * 密钥key
	 */
	private String accessKey;

	/**
	 * 密钥Secret
	 */
	private String accessSecret;

	/**
	 * bucketName
	 */
	private String bucketName;

}
