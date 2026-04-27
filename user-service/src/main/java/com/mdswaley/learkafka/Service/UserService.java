package com.mdswaley.learkafka.Service;

import com.mdswaley.learkafka.Repository.UserRepo;
import com.mdswaley.learkafka.dto.CreateUserRequestDTO;
import com.mdswaley.learkafka.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    public void createUser(CreateUserRequestDTO createUserRequestDTO){
        User user = modelMapper.map(createUserRequestDTO, User.class);
        userRepo.save(user);
    }
}
