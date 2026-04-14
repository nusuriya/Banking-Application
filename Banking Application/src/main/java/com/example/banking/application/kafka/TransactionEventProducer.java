
package com.example.banking.application.kafka;

import com.example.banking.application.event.TransactionEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class TransactionEventProducer {

    private final KafkaTemplate<String, TransactionEvent> kafkaTemplate;

    public TransactionEventProducer(KafkaTemplate<String, TransactionEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Retryable(
            value = Exception.class,
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000)
    )
    public void publish(TransactionEvent event) {
        System.out.printf("Publishing event to Kafka: {}", event);
        kafkaTemplate.send("transactions", event.getTransactionId(), event);
    }

    @Recover
    public void recover(Exception ex, TransactionEvent event) {
        System.err.println("Kafka publish failed for txn " + event.getTransactionId());
    }
}
