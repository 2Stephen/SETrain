package com.whut.backend.controller;


import com.github.pagehelper.PageInfo;
import com.whut.backend.common.Params;
import com.whut.backend.common.Result;
import com.whut.backend.entity.Bank;
import com.whut.backend.entity.Question;
import com.whut.backend.entity.QuestionIndex;
import com.whut.backend.entity.dto.QuestionDTO;
import com.whut.backend.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@Slf4j
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
    @ResponseBody
    @GetMapping("/getcurrentquestion")
    public ResponseEntity<Question> currentQuestion(@RequestParam Integer id) {
        Question question = questionService.getById(id);
        return ResponseEntity.ok(question);
    }


    @ResponseBody
    @GetMapping("/getcurrenttitles")
    public ResponseEntity<List<QuestionIndex>> currentTitles(@RequestParam Integer questionid) {
        List<QuestionIndex> question = questionService.getTitles(questionid);
        return ResponseEntity.ok(question);
    }

//    @ResponseBody
//    @GetMapping("/searchtitles")
//    public ResponseEntity<List<QuestionIndex>> searchTitles(@RequestParam String title) {
//        List<QuestionIndex> question = questionService.searchTitles(title);
//        return ResponseEntity.ok(question);
//    }

    @ResponseBody
    @GetMapping("/searchcontents")
    public ResponseEntity<PageInfo<Question>> searchContents(@RequestParam String content,
                                                         @RequestParam(defaultValue = "1") Integer page,
                                                         @RequestParam(defaultValue = "8") Integer pagesize,
                                                         @RequestParam(defaultValue = "1") Integer questionid){
        PageInfo<Question> question = questionService.searchContents(content,questionid,page,pagesize);
        return ResponseEntity.ok(question);
    }




    @GetMapping("/findBySearch")
    public Result findBySearch(@Param("params") Params params) {
        log.info("Controller findBySearch: {}", params);
        PageInfo<Question> pageList= questionService.findBySearch(params);
        return Result.success(pageList);
    }

    @PostMapping("/saveQuestion")
    public Result saveQuestion(@RequestBody QuestionDTO questionDTO) {
        log.info("Controller saveQuestion: {}", questionDTO);
        questionService.saveQuestion(questionDTO);
        return Result.success();
    }


}
