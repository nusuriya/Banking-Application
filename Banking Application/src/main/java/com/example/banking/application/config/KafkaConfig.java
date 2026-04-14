
package com.example.banking.application.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.retry.annotation.EnableRetry;

@Configuration
@EnableKafka
@EnableRetry
public class KafkaConfig {
}
