package com.tiz.learntobank.controller;

import com.tiz.learntobank.dto.UserDTO;
import com.tiz.learntobank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login (@RequestBody UserDTO user) {
        userService.login(user);
        return ResponseEntity.ok("Login successful");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register (@RequestBody UserDTO user) {
        userService.register(user);
        return ResponseEntity.ok("Register successful");
    }
}
