package com.example.quiz_service.model;

import lombok.Data;

@Data
public class QuestionWrapper {
    private Integer id;

    /* in database question_title (snake_case)     in java Model questionTitle (camel case) our ORM will handel snake_case and camel case. */
    private String questionTitle;

    private String option1;
    private String option2;
    private String option3;
    private String option4;

    public QuestionWrapper(Integer id, String questionTitle,  String option1, String option2, String option3, String option4) {
        this.id = id;
        this.questionTitle = questionTitle;

        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
    }
}