package com.realtimeweb.partyapp.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "pkc-75m1o.europe-west3.gcp.confluent.cloud:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put("security.protocol", "SASL_SSL");
        config.put("sasl.mechanism", "PLAIN");
        config.put("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username='YQ5TDXSG7NEJNQMX' password='fZQrdyuzuVni4H21PxXB4/L9XTAZs357eFcVTVxPGAKq2O66kwYvuzsWAeTaTrIv';");
        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
