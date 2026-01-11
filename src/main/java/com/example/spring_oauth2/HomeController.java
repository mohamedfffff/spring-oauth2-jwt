package com.example.spring_oauth2;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class HomeController {
    
    @GetMapping("/")
    public String Home() {
        return "Welcome! <a href='/user'>Click here to login</a>";
    }

    @GetMapping("/user")
    public Map<String, Object> getUser(@AuthenticationPrincipal OAuth2User principal) {
        return principal.getAttributes();
    }
    
}
