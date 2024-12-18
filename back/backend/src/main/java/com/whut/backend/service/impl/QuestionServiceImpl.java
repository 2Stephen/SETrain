package com.whut.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whut.backend.entity.Bank;
import com.whut.backend.entity.Question;
import com.whut.backend.entity.QuestionIndex;
import com.whut.backend.entity.QuestionBankView;
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

    @Override
    public Question getById(Integer id) {
        return QuestionMapper.selectById(id);
    }

    @Override
    public List<QuestionIndex> getTitles(Integer questionid) {
        return QuestionMapper.getTitles(questionid);
    }

    @Override
    public PageInfo<Question> searchContents(String content,Integer questionid, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Question>questions =  QuestionMapper.searchContents(content,questionid);
        return new PageInfo<>(questions);
    }

    @Override
    public PageInfo<QuestionBankView> searchAllContents(String content, Integer page, Integer pagesize) {
        PageHelper.startPage(page, pagesize);
        List<QuestionBankView> questions = QuestionMapper.searchAllContents(content);
        return new PageInfo<>(questions);
    }
}
