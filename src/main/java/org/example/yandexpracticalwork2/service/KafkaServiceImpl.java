package org.example.yandexpracticalwork2.service;

import lombok.RequiredArgsConstructor;
import org.example.yandexpracticalwork2.dto.KafkaMessage;
import org.example.yandexpracticalwork2.producer.KafkaProducer;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class KafkaServiceImpl implements KafkaService {

    private final KafkaProducer kafkaProducer;

    @Override
    public void sendMessage(String message) {

        KafkaMessage kafkaMessage = KafkaMessage.builder()
                .id(Math.abs(new Random().nextInt()))
                .message(message)
                .build();

        kafkaProducer.send(kafkaMessage);
    }
}
