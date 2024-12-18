package com.whut.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data

public class QuestionBankView {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("tags")
    private String tags;
    @TableField("question_title")
    private String question_title;
    @TableField("content")
    private String content;
    @TableField("answer")
    private String answer;
    @TableField("question_bank_title")
    private String question_bank_title;
    //id, question_title, tags, question_bank_title
}
