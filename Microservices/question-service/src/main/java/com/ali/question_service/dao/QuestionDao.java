package com.ali.question_service.dao;


import com.ali.question_service.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {


    //if we've a column inside our database. we can directly apply findBy<Column name>. this will return the values from coulmn.
   List<Question> findByCategory(String category);


   //Custom Method with Custom native JPA Query
   @Query(value = "SELECT * FROM question q Where q.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, int numQ);
}
