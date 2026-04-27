package com.mdswaley.learkafka.event;

import lombok.Data;

@Data
public class UserCreatedEvent {
    private Long id;
    private String email;
}
