package com.whut.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whut.backend.entity.Bank;
import com.whut.backend.entity.Question;
import com.whut.backend.entity.QuestionIndex;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {

    List<Question> getQuestionList(@Param("index") Integer index);

    List<Bank> getBankList();

    List<QuestionIndex> getTitles(Integer questionid);

    List<Question> searchContents(@Param("content")String content, @Param("questionid")Integer questionid);
}
