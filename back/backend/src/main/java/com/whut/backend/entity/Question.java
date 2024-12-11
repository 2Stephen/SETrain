package com.whut.backend.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Question {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("title")
    private String title;
    @TableField("content")
    private String content;
    @TableField("tags")
    private String tags;
    @TableField("answer")
    private String answer;
    @TableField("userId")
    private Integer userId;
}
