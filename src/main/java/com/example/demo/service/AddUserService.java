package com.example.demo.service;

import com.example.demo.database.entity.User;
import com.example.demo.database.repository.UserRepository;
import com.example.demo.dto.UserRegistrationRq;
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
public class AddUserService {

    private final UserRepository userRepository;

    private final ObjectMapper objectMapping;

    @Transactional
    public ResponseEntity<String> addUser(UserRegistrationRq userRegistrationRq){ // todo мб индекс на email? +  valid
        try {
            log.info("Принят запрос для сохранения нового участника " + objectMapping.writeValueAsString(userRegistrationRq));

            Optional<User> userOptional = userRepository.findUserByEmail(userRegistrationRq.getEmail());
            User user;
            if (userOptional.isPresent()){
                user = userOptional.get();
                log.info("Данный пользователь уже зарегистрирован в системе " + userRegistrationRq.getEmail());
            } else{
                log.info("Пользователь отсутствует");
                String salt = generateSalt();
                user = User.builder()
                        .username(userRegistrationRq.getUsername())
                        .email(userRegistrationRq.getEmail())
                        .name(userRegistrationRq.getName())
                        .surname(userRegistrationRq.getSurname())
                        .userSalt(salt)
                        .passwordHash(hashPassword(userRegistrationRq.getPassword(), salt))
                        .build();
                userRepository.save(user);
            }
            return new ResponseEntity<>(user.getId().toString(), HttpStatus.OK);
        } catch (JsonProcessingException | RuntimeException | NoSuchAlgorithmException e) {
            log.error("Внутрення ошибка сервиса " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[2];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }


    public static String hashPassword(String password, String salt) throws NoSuchAlgorithmException {
        String saltedPassword = password + salt;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(saltedPassword.getBytes(StandardCharsets.UTF_8));
        String fullHash = Base64.getEncoder().encodeToString(hashBytes);
        return fullHash.substring(0, Math.min(30, fullHash.length()));
    }
}
