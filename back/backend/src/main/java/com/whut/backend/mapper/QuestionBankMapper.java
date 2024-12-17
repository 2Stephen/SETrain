package com.whut.backend.mapper;

import com.whut.backend.entity.QuestionBank;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author Demon
* @description 针对表【question_bank(题库)】的数据库操作Mapper
* @createDate 2024-12-16 20:01:07
* @Entity generator.entity.QuestionBank
*/
public interface QuestionBankMapper extends BaseMapper<QuestionBank> {

    QuestionBank getQuestionBankById(Integer id);

    void deleteQuestionBankById(Integer id);

    void insertQuestionBank(QuestionBank questionBank);

    void updateQuestionBank(QuestionBank questionBank);


    List<QuestionBank> getAllQuestionBank();
}




