package com.mdswaley.learkafka.Service;

import com.mdswaley.learkafka.Repository.UserRepo;
import com.mdswaley.learkafka.dto.CreateUserRequestDTO;
import com.mdswaley.learkafka.entity.User;
import com.mdswaley.learkafka.event.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final KafkaTemplate<Long, UserCreatedEvent> kafkaTemplate;

    public void createUser(CreateUserRequestDTO createUserRequestDTO){
        User user = modelMapper.map(createUserRequestDTO, User.class);
        User saveUser = userRepo.save(user);

        UserCreatedEvent userCreatedEvent = modelMapper.map(saveUser, UserCreatedEvent.class);

    }
}
