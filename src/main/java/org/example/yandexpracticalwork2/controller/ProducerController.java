package org.example.yandexpracticalwork2.controller;

import lombok.RequiredArgsConstructor;
import org.example.yandexpracticalwork2.service.KafkaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/producer")
@RequiredArgsConstructor
public class ProducerController {

    private final KafkaService kafkaService;

    @GetMapping("sendMessage")
    public void sendMessage(@RequestParam String message) {
        kafkaService.sendMessage(message);
    }

}
