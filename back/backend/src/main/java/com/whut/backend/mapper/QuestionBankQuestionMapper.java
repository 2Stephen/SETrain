package com.whut.backend.mapper;

import com.whut.backend.entity.QuestionBankQuestion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author Demon
* @description 针对表【question_bank_question(题库题目)】的数据库操作Mapper
* @createDate 2024-12-17 16:59:30
* @Entity com.whut.backend.entity.QuestionBankQuestion
*/
public interface QuestionBankQuestionMapper extends BaseMapper<QuestionBankQuestion> {

    Integer getQuestionBankIdByQuestionId(Integer questionId);

    void updateQuestionBankQuestion(QuestionBankQuestion questionBankQuestion);



    void insertQuestionBankQuestion(QuestionBankQuestion questionBankQuestion);
}




