package com.whut.backend.controller;


import com.whut.backend.POJO.RegisterData;
import com.whut.backend.POJO.UserData;
import com.whut.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class UserController {
    private final UserService UserService;
    @Autowired
    public UserController(com.whut.backend.service.UserService userService) {
        UserService = userService;
    }
    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserData userData) {
        String username = userData.getUsername();
        String password = userData.getPassword();
        boolean login = UserService.login(username, password);
        if (login) {
            return ResponseEntity.ok("Login success");
        } else {
            return ResponseEntity.badRequest().body("Login failed");
        }
    }

    @ResponseBody
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterData registerData) {
        String username = registerData.getUsername();
        String password = registerData.getPassword();
        String email = registerData.getEmail();
        boolean register = UserService.register(username, password, email);
        if (register) {
            return ResponseEntity.ok("Register success");
        } else {
            return ResponseEntity.badRequest().body("Register failed");
        }
    }

}
