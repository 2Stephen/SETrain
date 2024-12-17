package com.whut.backend.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName question_bank_question
 */
@TableName(value ="question_bank_question")
@Data
public class QuestionBankQuestion implements Serializable {
    private Long id;
    private Long questionBankId;
    private Long questionId;

    private static final long serialVersionUID = 1L;
}