package com.whut.backend.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/api")
public class UserController {
    @ResponseBody
    @GetMapping("/login")
    public String login() {
        return "ok";
    }

}
