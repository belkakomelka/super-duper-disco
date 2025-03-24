package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistrationDto {
    private Long id;

    private String username;

    private String surname;

    private String name;

    private String email;

    private String password;
}
