package org.example.yandexpracticalwork2.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KafkaMessage {
    private Integer id;
    private String message;
}
