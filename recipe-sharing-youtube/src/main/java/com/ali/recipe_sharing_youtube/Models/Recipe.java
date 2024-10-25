package com.ali.recipe_sharing_youtube.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;

    //w.r.t recipe --> one user can create many recipes //  and one recipe have one user
    @ManyToOne
    private User user;
    private String image;
    private String description;
    private boolean bagitarian;
    private LocalDateTime createdAt;
    private List<Long> likes = new ArrayList<>();

}
