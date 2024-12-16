package com.whut.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whut.backend.entity.QuestionBank;
import com.whut.backend.entity.QuestionBankQuestion;
import com.whut.backend.mapper.QuestionBankMapper;
import com.whut.backend.service.QuestionBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Demon
* @description 针对表【question_bank(题库)】的数据库操作Service实现
* @createDate 2024-12-16 20:01:07
*/
@Service
public class QuestionBankServiceImpl extends ServiceImpl<QuestionBankMapper, QuestionBank>
    implements QuestionBankService{

    @Autowired
    private QuestionBankMapper questionBankMapper;

    @Override
    public QuestionBank getQuestionBankById(Integer id) {
        return questionBankMapper.getQuestionBankById(id);
    }

    @Override
    public void deleteQuestionBankById(Integer id) {
        questionBankMapper.deleteQuestionBankById(id);
    }

    @Override
    public void saveQuestionBank(QuestionBank questionBank) {
        //新增或者修改
        if(questionBank.getId() == null){
            questionBankMapper.insertQuestionBank(questionBank);
        }else{
            questionBankMapper.updateQuestionBank(questionBank);
        }
    }

    @Override
    public List<QuestionBankQuestion> getAllQuestionBankQuestion() {
        return questionBankMapper.getAllQuestionBankQuestion();
    }


}




