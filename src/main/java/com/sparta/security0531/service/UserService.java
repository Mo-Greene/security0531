package com.sparta.security0531.service;

import com.sparta.security0531.dto.UserRequestDto;
import com.sparta.security0531.model.User;
import com.sparta.security0531.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Map<String, String> validateHandle(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("html_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    public void postUser(UserRequestDto requestDto){
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String password2 = passwordEncoder.encode(requestDto.getPassword());
        User user = new User(username, password, password2);
        userRepository.save(user);
    }
}