package com.whut.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whut.backend.common.Params;
import com.whut.backend.entity.Bank;
import com.whut.backend.entity.Question;
import com.whut.backend.entity.QuestionIndex;
import com.whut.backend.entity.QuestionBankView;
import com.whut.backend.entity.dto.QuestionDTO;
import com.whut.backend.mapper.QuestionMapper;
import com.whut.backend.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

    @Resource
    private QuestionMapper questionMapper;

    @Override
    public PageInfo<Question> getQuestionList(Integer index, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Question> question = questionMapper.getQuestionList(index);
        return new PageInfo<>(question);

    }

    @Override
    public List<Bank> getBankList() {
        return questionMapper.getBankList();
    }

    @Override
    public Question getById(Integer id) {
        return questionMapper.selectById(id);
    }

    @Override
    public List<QuestionIndex> getTitles(Integer questionid) {
        return questionMapper.getTitles(questionid);
    }

    @Override
    public PageInfo<Question> searchContents(String content,Integer questionid, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Question>questions =  questionMapper.searchContents(content,questionid);
        return new PageInfo<>(questions);
    }

    @Override
    public PageInfo<QuestionBankView> searchAllContents(String content, Integer page, Integer pagesize) {
        PageHelper.startPage(page, pagesize);
        List<QuestionBankView> questions = QuestionMapper.searchAllContents(content);
        return new PageInfo<>(questions);
    public PageInfo<Question> findBySearch(Params params) {
        log.info("Service findBySearch: {}", params);
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        List<Question> list = questionMapper.findBySearch(params);
        return PageInfo.of(list);
    }

    @Override
    public void saveQuestion(QuestionDTO questionDTO) {
        Question question = new Question();
        BeanUtils.copyProperties(questionDTO, question);



        if(question.getId() == null){
            log.info("新增题目: {}", question);
            questionMapper.insertQuestion(question);
        }else{
            log.info("修改题目: {}", question);
            questionMapper.updateQuestion(question);
        }


        //TODO 题目题库表插入 这里可以体现事务 @Transactional


    }
}
