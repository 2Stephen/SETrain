package com.whut.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whut.backend.entity.QuestionBankQuestion;
import com.whut.backend.service.QuestionBankQuestionService;
import com.whut.backend.mapper.QuestionBankQuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author Demon
* @description 针对表【question_bank_question(题库题目)】的数据库操作Service实现
* @createDate 2024-12-17 16:59:30
*/
@Service
public class QuestionBankQuestionServiceImpl extends ServiceImpl<QuestionBankQuestionMapper, QuestionBankQuestion>
    implements QuestionBankQuestionService{

    @Autowired
    private QuestionBankQuestionMapper questionBankQuestionMapper;
    @Override
    public Integer getQuestionBankIdByQuestionId(Integer questionId) {
        return questionBankQuestionMapper.getQuestionBankIdByQuestionId(questionId);
    }

    @Override
    public void saveQuestionBankQuestion(QuestionBankQuestion questionBankQuestion) {
        // 如果存在则更新
        Integer questionId=questionBankQuestion.getQuestionId();
        if(questionBankQuestionMapper.getQuestionBankIdByQuestionId(questionId)!=null){
            questionBankQuestionMapper.updateQuestionBankQuestion(questionBankQuestion);
        }else{
            // 不存在就新增
            questionBankQuestionMapper.insertQuestionBankQuestion(questionBankQuestion);
        }

    }


}




