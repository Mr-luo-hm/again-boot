package com.again.boot.starter.pv.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.List;

/**
 * @author create by 罗英杰 on 2021/11/24
 * @description:
 */
@Data
@ConfigurationProperties(prefix = "again.boot.pv")
public class PvProperties {

	private List<String> ignoreUrlPatterns = Arrays.asList("/actuator/**", "/webjars/**", "/favicon.ico");

}
