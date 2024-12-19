package com.whut.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whut.backend.common.Params;
import com.whut.backend.entity.Bank;
import com.whut.backend.entity.Question;
import com.whut.backend.entity.QuestionBankQuestion;
import com.whut.backend.entity.QuestionIndex;
import com.whut.backend.entity.QuestionBankView;
import com.whut.backend.entity.dto.QuestionDTO;
import com.whut.backend.handler.CustomException;
import com.whut.backend.mapper.QuestionMapper;
import com.whut.backend.service.QuestionBankQuestionService;
import com.whut.backend.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

    @Resource
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionBankQuestionService questionBankQuestionService;

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
        List<QuestionBankView> questions = questionMapper.searchAllContents(content);
        return new PageInfo<>(questions);
    }
    public PageInfo<Question> findBySearch(Params params) {
        log.info("Service findBySearch: {}", params);
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        List<Question> list = questionMapper.findBySearch(params);
        return PageInfo.of(list);
    }

    @Override
    //这里可以体现事务 @Transactional
    //两张表同时修改，  都成功， 或者都回滚 不知道这算不算应用事务
    @Transactional
    public void saveQuestion(QuestionDTO questionDTO) {
        Question question = new Question();
        BeanUtils.copyProperties(questionDTO, question);

        //未输入/输入空白的题目标题
        if (question.getTitle() == null || question.getTitle().trim().isEmpty()) {
            throw new CustomException("请输入题目标题");
        }
        //未选择所属题库
        if(questionDTO.getQuestionBankId() == null){
            throw new CustomException("请选择所属题库");
        }

        if(question.getId() == null){
            log.info("新增题目: {}", question);
            //新增题目表数据,并拿到新增题目的id (xml中配置了主键回填),在question类中已经赋值了id
            questionMapper.insertQuestion(question);
        }else{
            log.info("更新题目: {}", question);
            //更新题目表数据
            questionMapper.updateQuestion(question);
        }

        //新增或更新题目题库表数据
        QuestionBankQuestion questionBankQuestion=new QuestionBankQuestion();
        questionBankQuestion.setQuestionBankId(questionDTO.getQuestionBankId());
        //主键回填后，question.getId()已经赋值了
        questionBankQuestion.setQuestionId(question.getId());
        log.info("新增或更新题目题库表数据: {}", questionBankQuestion);
        questionBankQuestionService.saveQuestionBankQuestion(questionBankQuestion);
    }

    @Override
    public void deleteQuestionById(Integer id) {
        //删除题目表数据
        questionMapper.deleteQuestionById(id);
        //删除题目题库表数据
        //TODO 留给触发器实现
    }

    @Override
    @Transactional
    // 这里可以体现事务 要么全都删除，要么一个都不删除，但不好演示出来
    public void deleteQuestionBatch(List<Question> questionList) {
        for (Question question : questionList) {
            log.info("删除题目: {}", question);
            //删除题目表数据
            questionMapper.deleteQuestionById(question.getId());
            //删除题目题库表数据
            //TODO 留给触发器实现
        }
    }

    @Override
    public void generateQuestionCSV() {
        questionMapper.generateQuestionCSV();
    }

    @Override
    @Transactional
    public void refreshQuestionCSV(String fileRealName) {
        // 删除临时表（如果存在）
        questionMapper.dropTempTable();

        // 创建临时表
        questionMapper.createTempTable();

        // 从CSV文件加载数据到临时表
        questionMapper.loadDataFromFile(fileRealName);

        // 将数据从临时表导入到question表
        questionMapper.insertIntoQuestion();

        // 将数据从临时表导入到question_bank_question表
        questionMapper.insertIntoQuestionBankQuestion();

        // 删除临时表
        questionMapper.dropTempTableAfterUse();
    }


}
