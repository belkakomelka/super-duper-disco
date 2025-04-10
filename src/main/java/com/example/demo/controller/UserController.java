package com.example.demo.controller;

import com.example.demo.dto.UserRegistrationRq;
import com.example.demo.service.AddUserService;
import com.example.demo.service.GetUserInfoService;
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

    @Autowired
    GetUserInfoService getUserInfoService;

    @PostMapping("/add-user")
    public ResponseEntity<String> addUser(@RequestBody UserRegistrationRq userRegistrationRq) {
        return addUserService.addUser(userRegistrationRq);
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<String> getUser(@PathVariable("id") Long id) {
        return getUserInfoService.getUser(id);
    }



    // todo Valid+RestControllerAdvice
}