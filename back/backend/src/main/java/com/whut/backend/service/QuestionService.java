package com.whut.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.whut.backend.entity.Question;

public interface QuestionService extends IService<Question> {

    PageInfo<Question> getQuestionList(Integer index, Integer page, Integer pageSize);
}
