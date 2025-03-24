package com.example.demo.service;

import com.example.demo.database.entity.User;
import com.example.demo.database.repository.UserRepository;
import com.example.demo.dto.UserRegistrationDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

@Service
@Slf4j
public class AddUserService {
    private final UserRepository userRepository;
    private final ObjectMapper objectMapping;

    public AddUserService(UserRepository userRepository, ObjectMapper objectMapping) {
        this.userRepository = userRepository;
        this.objectMapping = objectMapping;
    }

    public ResponseEntity<String> addUser(UserRegistrationDto userRegistrationDto){ // todo мб индекс на email? +  valid
        try {
            log.info("Принят запрос для сохранения нового участника " + objectMapping.writeValueAsString(userRegistrationDto));

            Optional<User> userOptional = userRepository.findUserByEmail(userRegistrationDto.getEmail());
            User user;
            if (userOptional.isPresent()){
                user = userOptional.get();
                log.info("Данный пользователь уже зарегистрирован в системе " + userRegistrationDto.getEmail());
            } else{
                log.info("Пользователь отсутствует");
                String salt = generateSalt();
                user = User.builder()
                        .email(userRegistrationDto.getEmail())
                        .name(userRegistrationDto.getName())
                        .surname(userRegistrationDto.getSurname())
                        .userSalt(salt)
                        .passwordHash(hashPassword(userRegistrationDto.getPassword(), salt))
                        .build();
            }
            return new ResponseEntity<>(user.getId().toString(), HttpStatus.OK);
        } catch (JsonProcessingException | RuntimeException | NoSuchAlgorithmException e) {
            log.error("Внутрення ошибка сервиса " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }


    public static String hashPassword(String password, String salt) throws NoSuchAlgorithmException {
        String saltedPassword = password + salt;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(saltedPassword.getBytes());
        return Base64.getEncoder().encodeToString(hashBytes);
    }
}
