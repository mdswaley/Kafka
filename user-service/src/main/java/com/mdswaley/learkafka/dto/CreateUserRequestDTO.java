package com.mdswaley.learkafka.dto;

import lombok.Data;

@Data
public class CreateUserRequestDTO {
    private Long id;
    private String name;
    private String email;
}
