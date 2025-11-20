# Простейшее приложение для работы с кластером Kafka

## Описание
Приложение разработано на базе **Spring Boot** и демонстрирует базовые принципы работы с Kafka

В составе приложения:
- **Продюсер**, отправляющий сообщения в Kafka
- **Два консьюмера**:
    - первый читает **по одному сообщению**
    - второй читает сообщения **batch’ами**, после накопления **2000 байт**

Кластер Kafka состоит из **двух брокеров**, работает на базе **KRaft**, и использует топик с **3 партициями** и **2 репликами**

---

## Запуск проекта

1. **Клонировать репозиторий:**
   ```bash
   git clone https://github.com/shoxied/test-task-file.git
2. Перейти в корневой каталог проекта
3. Запустить Kafka-кластер:
   ```bash
   docker compose -f docker-compose-kafka-kraft.yaml up -d
4. Создать топик
   ```bash
   sudo docker exec -it kafka-kafka-0-1 /opt/bitnami/kafka/bin/kafka-topics.sh --create --topic practical-work-2 --bootstrap-server localhost:9092 --partitions 3 --replication-factor 2
5. Собрать проект
   ```bash
   mvn clean package
6. Запустить приложение
   ```bash
   java -jar ./target/yandex-kafka-practical-work-2-0.0.1-SNAPSHOT.jar

## Проверка работы

- Swagger доступен по адресу:  
  `http://localhost:8080/swagger-ui.html`
- Отправлять сообщения можно через контроллер
- В логах приложения видно:
    - отправку сообщения продюсером
    - обработку сообщения первым консьюмером
    - обработку batch вторым консьюмером после накопления ~2000 байт