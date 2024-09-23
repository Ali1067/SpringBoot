package com.example.quiz_service.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
//@Data    // Data is Lombok as it'll automatically creates the getter and setters
@Getter
@Setter
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;


    //This will create a sub-folder inside our database. which will have only ids of questions and quiz mapped.
    @ElementCollection
    private List<Integer> questionIds;


}
