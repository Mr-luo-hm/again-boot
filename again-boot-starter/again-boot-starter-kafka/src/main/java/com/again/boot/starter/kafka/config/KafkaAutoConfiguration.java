package com.again.boot.starter.kafka.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * @author create by 罗英杰 on 2021/8/26
 * @description:
 */
@Slf4j
@RequiredArgsConstructor
@EnableConfigurationProperties({ KafkaProperties.class })
public class KafkaAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean(KafkaExtendProducer.class)
	public KafkaExtendProducer<String, String> stringKafkaExtendProducer(KafkaProperties properties) {
		KafkaProducerBuilder builder = new KafkaProducerBuilder()
				.addAllBootstrapServers(properties.getBootstrapServers()).putAll(properties.getExtend());

		builder.keySerializer(properties.getKeySerializerClassName());
		builder.valueSerializer(properties.getValueSerializerClassName());
		return builder.build();
	}

	@Bean
	@Scope("prototype")
	@ConditionalOnMissingBean(KafkaConsumerBuilder.class)
	public KafkaConsumerBuilder consumerBuilder(KafkaProperties properties) {
		return new KafkaConsumerBuilder().addAllBootstrapServers(properties.getBootstrapServers())
				.keyDeserializer(properties.getKeyDeserializerClassName())
				.valueDeserializer(properties.getValueDeserializerClassName()).groupId(properties.getGroupId());
	}

}
