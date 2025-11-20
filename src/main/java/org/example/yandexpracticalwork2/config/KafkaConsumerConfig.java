package org.example.yandexpracticalwork2.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.example.yandexpracticalwork2.dto.KafkaMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration("batch")
@EnableKafka
public class KafkaConsumerConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Bean
    public ConsumerFactory<String, KafkaMessage> consumerFactorySingle() {
        Map<String, Object> props = new HashMap<>();
        //Адрес брокера
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        //группа консьюмера
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "1");
        //десириалайзер ключа
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        //десириалайзер сообщения
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        //обертка над JsonDeserializer для обработки ошибок
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
        //говорим что доверяем данному классу и можно его десириализовать
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "org.example.yandexpracticalwork2.dto");
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "org.example.yandexpracticalwork2.dto.KafkaMessage");
        //автокоммит оффсетов
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");

        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaMessage> kafkaListenerContainerFactorySingle() {

        ConcurrentKafkaListenerContainerFactory<String, KafkaMessage> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactorySingle());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, KafkaMessage> consumerFactoryBatch() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "2");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "org.example.yandexpracticalwork2.dto");
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "org.example.yandexpracticalwork2.dto.KafkaMessage");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        //минимально берем 2000 байтов за раз
        props.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, "2000");
        //максимальное время ожидания 2000 байтов
        props.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, "2147483647");
        //максимальное время ожидания ответа на запрос
        props.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, "2147483647");

        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaMessage> kafkaListenerContainerFactoryBatch() {

        ConcurrentKafkaListenerContainerFactory<String, KafkaMessage> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryBatch());
        return factory;
    }


}
