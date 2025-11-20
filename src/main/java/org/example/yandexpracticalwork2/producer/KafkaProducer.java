package org.example.yandexpracticalwork2.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.yandexpracticalwork2.dto.KafkaMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, KafkaMessage> kafkaTemplate;

    public void send(KafkaMessage message) {
        log.info("Sending message: id - {}, message - {}", message.getId(), message.getMessage());
        kafkaTemplate.send("practical-work-2", message);
    }

}
