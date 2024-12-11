package com.whut.backend.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/question")
public class QuestionController {
    @ResponseBody
    @GetMapping("/bankindex")
    public ResponseEntity<> bankIndex(@RequestParam Integer index) {


    }
}
