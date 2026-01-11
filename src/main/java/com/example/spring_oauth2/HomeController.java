package com.example.spring_oauth2;


import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class HomeController {

    private JwtUtils jwtUtils;

    public HomeController(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }
    
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/auth-success")
    public String authSuccess(@AuthenticationPrincipal OAuth2User principal, Model model) {
        String email = principal.getAttribute("email");
        String name = principal.getAttribute("name");

        String token =  jwtUtils.generateToken(email, name);

        model.addAttribute("email", email);
        model.addAttribute("name", name);
        return "dashboard";
    }
    
}
