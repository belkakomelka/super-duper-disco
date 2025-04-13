package com.example.demo.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistrationRq {
    @NotBlank
    private String username;

    @NotBlank
    private String surname;

    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
