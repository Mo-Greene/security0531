package com.sparta.security0531.controller;

import com.sparta.security0531.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    //기존이였으면 static에 index 찾았겠지만 이제는 동적으로 바꿔줬다.
    @GetMapping("/")
    // 밑에 내용은 왜워야됨 혹은 복붙 하자
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        model.addAttribute("username", userDetails.getUsername());
        // 동적 index 파일로 간다~
        return "index";
    }
}