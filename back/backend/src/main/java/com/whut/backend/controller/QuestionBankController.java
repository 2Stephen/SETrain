package com.whut.backend.controller;

import com.whut.backend.common.Result;
import com.whut.backend.entity.QuestionBank;
import com.whut.backend.service.QuestionBankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questionBank")
@Slf4j
public class QuestionBankController {
    @Autowired
    private QuestionBankService questionBankService;
    @GetMapping("/getQuestionBankById/{id}")
    public Result getProductById(@PathVariable("id") Integer id) {
        log.info("Controller getQuestionBankById: {}", id);
        QuestionBank questionBank = questionBankService.getQuestionBankById(id);
        return Result.success(questionBank);
    }

    @DeleteMapping("/deleteQuestionBankById/{id}")
    public Result deleteProduct(@PathVariable("id") Integer id) {
        log.info("Controller deleteQuestionBank: {}", id);
        questionBankService.deleteQuestionBankById(id);
        return Result.success();
    }
    @PostMapping("/saveQuestionBank")
    public Result saveQuestionBank(@RequestBody QuestionBank questionBank) {
        log.info("Controller saveQuestionBank: {}", questionBank);
        questionBankService.saveQuestionBank(questionBank);
        return Result.success();
    }

    @GetMapping("/getAllQuestionBankQuestion")
    public Result getAllQuestionBankQuestion() {
        log.info("Controller getAllQuestionBankQuestion");
        return Result.success(questionBankService.getAllQuestionBankQuestion());
    }

}
