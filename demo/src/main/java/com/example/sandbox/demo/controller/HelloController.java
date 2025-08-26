package com.example.sandbox.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.sandbox.demo.model.User;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Mo!";
    }

    // http://localhost:3006/api/greet?name=Mo&rate=engineer
    @GetMapping("/greet")
    public Map<String, Object> Greet(@RequestParam String name, @RequestParam String rate) {
        User human = new User(name, rate);

        return Map.of(
            "greeting", "Greetings human named: " + name + "!",
            "User", human
        );
    }

    @GetMapping("/ping")
    public Map<String, String> ping() {
        return Map.of("ping", "pong");
    }
}
