package org.example.yandexpracticalwork2.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.example.yandexpracticalwork2.dto.KafkaMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BatchMessageConsumer {

    @KafkaListener(topics = "practical-work-2", groupId = "2", batch = "true", containerFactory = "kafkaListenerContainerFactoryBatch")
    public void consumeBatchMessages(ConsumerRecords<String, KafkaMessage> records) {
        log.info("Consumed batch messages: {}", records.count());
        for (ConsumerRecord<String, KafkaMessage> cr : records) {
            KafkaMessage message = cr.value();
            log.info("Message received: id - {}, message - {}", message.getId(), message.getMessage());
        }
    }

}
