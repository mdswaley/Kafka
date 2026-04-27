package com.mdswaley.learkafka.Controller;

import com.mdswaley.learkafka.Service.UserService;
import com.mdswaley.learkafka.dto.CreateUserRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @Value("${kafka.topic.user-random-topic}")
    private String KAFKA_USER_RANDOM_TOPIC;

    private UserService userService;

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

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequestDTO createUserRequestDTO){
        userService.createUser(createUserRequestDTO);
        return ResponseEntity.ok("User create Successfully.");
    }
}
