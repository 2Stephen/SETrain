package com.whut.backend.controller;

import com.whut.backend.common.Result;
import com.whut.backend.service.QuestionBankQuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questionBankQuestion")
@Slf4j
public class QuestionBankQuestionController {
    @Autowired
    private QuestionBankQuestionService questionBankQuestionService;
    @RequestMapping("/getQuestionBankIdByQuestionId/{id}")
    public Result getQuestionBankQuestionById(@PathVariable("id") Long questionId) {
        log.info("Controller getQuestionBankQuestionById questionId: {}", questionId);
        Long questionBankId = questionBankQuestionService.getQuestionBankIdByQuestionId(questionId);
        log.info("Controller getQuestionBankQuestionById questionBankId: {}", questionBankId);
        return Result.success(questionBankId);
    }
}
