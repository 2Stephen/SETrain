package com.whut.backend.controller;


import com.github.pagehelper.PageInfo;
import com.whut.backend.entity.Bank;
import com.whut.backend.entity.Question;
import com.whut.backend.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }
    @ResponseBody
    @GetMapping("/paginatedquestions")
    public ResponseEntity<PageInfo<Question>> bankIndex(
            @RequestParam Integer index,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "8") Integer pagesize) {
        PageInfo<Question> questionList = questionService.getQuestionList(index, page, pagesize);
        return ResponseEntity.ok(questionList);
    }

    @ResponseBody
    @GetMapping("/getbanklist")
    public ResponseEntity<List<Bank>> banks() {
        List<Bank> bankList = questionService.getBankList();
        return ResponseEntity.ok(bankList);
    }
}
