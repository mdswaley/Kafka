package com.mdswaley.learkafka.Service;

import com.mdswaley.learkafka.Repository.UserRepo;
import com.mdswaley.learkafka.dto.CreateUserRequestDTO;
import com.mdswaley.learkafka.entity.User;
import com.mdswaley.learkafka.event.UserCreatedEvent1;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${kafka.topic.user-created-topic}")
    private String KAFKA_USER_CREATED_TOPIC;

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final KafkaTemplate<Long, UserCreatedEvent1> kafkaTemplate;

    public void createUser(CreateUserRequestDTO createUserRequestDTO){
        User user = modelMapper.map(createUserRequestDTO, User.class);
        User saveUser = userRepo.save(user);

        UserCreatedEvent1 userCreatedEvent1 = modelMapper.map(saveUser, UserCreatedEvent1.class);
        kafkaTemplate.send(KAFKA_USER_CREATED_TOPIC, userCreatedEvent1.getId(), userCreatedEvent1);

    }
}
