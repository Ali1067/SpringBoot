package com.example.quiz_service.service;


import com.example.quiz_service.Feign.QuizInterface;
import com.example.quiz_service.dao.QuizDao;
import com.example.quiz_service.model.Question;
import com.example.quiz_service.model.QuestionWrapper;
import com.example.quiz_service.model.Quiz;
import com.example.quiz_service.model.Response;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Integer> questions = quizInterface.getQuestionsForQuiz(category,numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz); //save the quiz to db
        return new ResponseEntity<>("success", HttpStatus.OK);

    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Quiz quiz = quizDao.findById(id).get();   //find if id exist in table
List<Integer> questionIds = quiz.getQuestionIds();

ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestiosnFromIid(questionIds);
        return questions;

    }

    //get the count of right answer.
    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get();           //get the quiz by id from db and save into model
//        List<Question> questions = quiz.getQuestions();     //get the questions from quiz, to get right answer from db
//        // Responses = user submited answer & question have already right answer in db
        int rightanswer = 0;
        int i = 0;
//        for (Response response : responses){
//            if (response.getResponse().equals(questions.get(i).getRightAnswer()))
//                rightanswer++;
//
//        i++;
//
//        }
        return  new ResponseEntity<>(rightanswer, HttpStatus.OK);
    }




}
