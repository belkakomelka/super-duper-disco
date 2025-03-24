package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistrationRs {
    private String username;

    private String surname;

    private String name;

    private String email;
    // todo еще какие-то поля вернутся
}
