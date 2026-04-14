
package com.example.banking.application.kafka;

import com.example.banking.application.event.TransactionEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TransactionEventConsumer {

    private final Set<String> processed = ConcurrentHashMap.newKeySet();

    @KafkaListener(topics = "transactions", groupId = "banking-group")
    public void consume(TransactionEvent event) {

        if (processed.contains(event.getTransactionId())) {
            return; // idempotent consumer
        }

        // audit / notification / logging
        System.out.println("Consumed event: " + event.getTransactionId());

        processed.add(event.getTransactionId());
    }
}
