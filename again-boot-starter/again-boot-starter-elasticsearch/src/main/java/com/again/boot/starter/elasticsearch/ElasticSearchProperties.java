package com.again.boot.starter.elasticsearch;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author create by 罗英杰 on 2021/8/26
 * @description:
 */
@Data
@ConfigurationProperties(prefix = "again.boot.starter.es")
public class ElasticSearchProperties {

	private String host;

	private Integer port;

	private Integer port1;

	private Integer port2;

	private String index;

	private String type;

	private String clusterName;

	private Integer tcpPort;

	private String userName;

	private String password;

}
