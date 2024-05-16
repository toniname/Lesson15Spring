package com.example.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafTestController {

    @GetMapping("/thymeleaf-test")
    public String test() {
        return "test";
    }

}