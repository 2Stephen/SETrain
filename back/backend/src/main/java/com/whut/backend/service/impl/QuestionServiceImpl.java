package com.whut.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whut.backend.entity.Bank;
import com.whut.backend.entity.Question;
import com.whut.backend.mapper.QuestionMapper;
import com.whut.backend.service.QuestionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

    @Resource
    private QuestionMapper QuestionMapper;

    @Override
    public PageInfo<Question> getQuestionList(Integer index, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Question> question = QuestionMapper.getQuestionList(index);
        return new PageInfo<>(question);

    }

    @Override
    public List<Bank> getBankList() {
        return QuestionMapper.getBankList();
    }
}
