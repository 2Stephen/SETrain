package com.whut.backend.service;

import com.whut.backend.entity.QuestionBankQuestion;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Demon
* @description 针对表【question_bank_question(题库题目)】的数据库操作Service
* @createDate 2024-12-17 16:59:30
*/
public interface QuestionBankQuestionService extends IService<QuestionBankQuestion> {

    Long getQuestionBankIdByQuestionId(Long questionId);

}
