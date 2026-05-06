package com.mdswaley.learkafka.event;

import lombok.Data;

@Data
public class UserCreatedEvent1 {
    private Long id;
    private String email;
}
