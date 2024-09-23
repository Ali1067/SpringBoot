package com.example.quiz_service.controller;


import com.example.quiz_service.model.QuestionWrapper;
import com.example.quiz_service.model.Response;
import com.example.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;



    @PostMapping("create")
    public ResponseEntity<String> quiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title  ){
    return quizService.createQuiz(category, numQ, title);

//        return new ResponseEntity<>("i'm here", HttpStatus.OK);
    }

    //id is the quiz id saved into our db
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions( Integer id){
        return quizService.getQuizQuestions(id);

    }

    //get the count of right answer.
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody  List<Response> responses){
        //@PathVariable Integer id = id of the quiz
        // @RequestBody  List<Response> responses = Response Model <Q_id and answer from user>
        return  quizService.calculateResult(id, responses);

    }




}
