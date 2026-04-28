package com.mdswaley.learkafka.notification_service.Consumer;

import com.mdswaley.learkafka.notification_service.event.UserCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
@Slf4j
public class UserKafkaConsumer {

    @KafkaListener(topics = "user-created-topic")
    public void handleUserCreatedTopic(UserCreatedEvent userCreatedEvent){
        log.info("handle user created: {}", userCreatedEvent);
    }

    @KafkaListener(topics = "user-random-topic")
    public void handleTopic1(String message){
        log.info("message received1: {}", message);
    }

    @KafkaListener(topics = "user-random-topic")
    public void handleTopic2(String message){
        log.info("message received2: {}", message);
    }

    @KafkaListener(topics = "user-random-topic")
    public void handleTopic3(String message){
        log.info("message received3: {}", message);
    }
}

// A consumer group is a set of consumers working together to read data from a topic.
