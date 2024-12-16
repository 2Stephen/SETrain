package com.whut.backend.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class QuestionIndex {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("title")
    private String title;

    @JsonIgnore
    @TableField("content")
    private String content;

    @JsonIgnore
    @TableField("tags")
    private String tags;

    @JsonIgnore
    @TableField("answer")
    private String answer;
}
