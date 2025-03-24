package com.example.demo.controller;

import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.service.AddUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    @Autowired
    AddUserService addUserService;
    @PostMapping("/add-user")
    public ResponseEntity<String> addUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        return addUserService.addUser(userRegistrationDto);
    }

    // todo Valid+RestControllerAdvice
}