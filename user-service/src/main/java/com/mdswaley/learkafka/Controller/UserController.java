package com.mdswaley.learkafka.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @Value("${kafka.topic.user-random-topic}")
    private String KAFKA_USER_RANDOM_TOPIC;

//    KafkaTemplate is a producer-side utility in Spring Kafka
//    Its job = send messages to Kafka topic
//    Flow:
//      Your API is called
//      KafkaTemplate.send() is triggered
//      Message is sent to Kafka broker
//      Kafka stores it in the topic
//      Done ✅
    private final KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/{message}")
    public ResponseEntity<String> sendMessage(@PathVariable String message){

        for (int i=0;i<1000;i++){
            kafkaTemplate.send(KAFKA_USER_RANDOM_TOPIC,""+i%2, message + i);
        }
//        kafkaTemplate.send("user-random-topic", message);
        return ResponseEntity.ok("message queued.");
    }
}
