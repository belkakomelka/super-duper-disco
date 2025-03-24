package com.example.demo.service;

import com.example.demo.database.entity.User;
import com.example.demo.database.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetUserInfoService {

    private final UserRepository userRepository;

    private final ObjectMapper objectMapping;

//    @Transactional
//    public ResponseEntity<String> getUser(Long id){
//        try {
//            log.info("Принят запрос для получения информации о пользователе с id = " + id);
//
//            Optional<User> userOptional = userRepository.findUserById(id);
//            User user;
//            if (userOptional.isPresent()){
//                user = userOptional.get();
//                log.info("Пользователь был найден");
//            } else{
//                log.info("Пользователь отсутствует или информация не найдена");
//            }
//            return new ResponseEntity<>(user, HttpStatus.OK); // map to userEntityRq
//        } catch (JsonProcessingException | RuntimeException | NoSuchAlgorithmException e) {
//            log.error("Внутрення ошибка сервиса " + e.getMessage());
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }



}
