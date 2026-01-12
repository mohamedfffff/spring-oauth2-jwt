package com.example.spring_oauth2.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class apiController {

    @GetMapping("api")
    public Map<String, String> testApi() {
        return Collections.singletonMap("message", "secure endpoint reaches");
    }
    
}
