package com.whut.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName question_bank_question
 */
@TableName(value ="question_bank_question")
@Data
public class QuestionBankQuestion implements Serializable {
    private Long id;

    private Long questionbankid;

    private Long questionid;

    private static final long serialVersionUID = 1L;
}