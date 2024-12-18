package com.whut.backend.entity.dto;


import lombok.Data;

@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String content;
    private String tags;
    private String answer;
    //题库id
    private Integer questionBankId;
}
