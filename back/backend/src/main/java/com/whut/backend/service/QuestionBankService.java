package com.whut.backend.service;

import com.whut.backend.entity.QuestionBank;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Demon
* @description 针对表【question_bank(题库)】的数据库操作Service
* @createDate 2024-12-16 20:01:07
*/
public interface QuestionBankService extends IService<QuestionBank> {

    QuestionBank getQuestionBankById(Integer id);


    void deleteQuestionBankById(Integer id);

    void saveQuestionBank(QuestionBank questionBank);


    List<QuestionBank> getAllQuestionBank();
}
