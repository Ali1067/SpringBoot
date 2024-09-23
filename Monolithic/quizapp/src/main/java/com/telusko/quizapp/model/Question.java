package com.telusko.quizapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Question {
/* our db have table name as question and our model name is also question. ORM will deal this. if our db table name is Question_All and model name is Question.  than we need to add annotation like @Entity(@Table "Question_All")
* Secondly, as we're using Lombok, that is why we don't need to create getter/setter. simply add @Data annotation with class name*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /* in database question_title (snake_case)     in java Model questionTitle (camel case) our ORM will handel snake_case and camel case. */
    private String questionTitle;
    private String category;
    private String option1;
    private String option2;
    private String option3;

    private String option4;
    private String rightAnswer;

    /*  in database difficultylevel which is a string (not snake_case) in java Model difficultylevel same string as db our ORM will consider it as string */
    private String difficultylevel;


}
