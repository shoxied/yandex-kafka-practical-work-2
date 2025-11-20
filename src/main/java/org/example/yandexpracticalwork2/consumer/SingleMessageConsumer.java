package org.example.yandexpracticalwork2.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.yandexpracticalwork2.dto.KafkaMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SingleMessageConsumer {

    @KafkaListener(topics = "practical-work-2", groupId = "1", containerFactory = "kafkaListenerContainerFactorySingle")
    public void consumeSingleMessage(KafkaMessage message) {
        log.info("Message received: id - {}, message - {}", message.getId(), message.getMessage());
    }

}
