package com.sparta.security0531.controller;

import com.sparta.security0531.dto.UserRequestDto;
import com.sparta.security0531.model.User;
import com.sparta.security0531.repository.UserRepository;
import com.sparta.security0531.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }
//예외처리 한값
    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(@Valid UserRequestDto requestDto, Errors errors, Model model) {
        // 회원 ID 중복 확인
        Optional<User> found = userRepository.findByUsername(requestDto.getUsername());
        if (found.isPresent()) {
            model.addAttribute("html_username", "중복되는 아이디가 존재");
            return "signup";
        } else if (requestDto.getPassword().contains(requestDto.getUsername())) {
            model.addAttribute("html_password", "비밀번호에는 아이디가 포함될 수 없어요");
            return "signup";
        } else if (errors.hasErrors()) {

            Map<String, String> errorResult = userService.validateHandle(errors);

            for (String key : errorResult.keySet()) {
                model.addAttribute(key, errorResult.get(key));
            }
            return "signup";
        } else if (!requestDto.getPassword().equals(requestDto.getPassword2())) {
            model.addAttribute("html_password2", "비밀번호와 똑같이 적어주숑");
            return "signup";
        }

        userService.postUser(requestDto);
        return "redirect:/user/login";
    }
}