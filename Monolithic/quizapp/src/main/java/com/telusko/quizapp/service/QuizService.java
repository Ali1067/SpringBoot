package com.telusko.quizapp.service;

import com.telusko.quizapp.dao.QuestionDao;
import com.telusko.quizapp.dao.QuizDao;
import com.telusko.quizapp.model.Question;
import com.telusko.quizapp.model.QuestionWrapper;
import com.telusko.quizapp.model.Quiz;
import com.telusko.quizapp.model.Response;
import lombok.extern.java.Log;
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
    QuestionDao questionDao;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        // To Fetch questions from database for quiz creation
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("success", HttpStatus.OK);

    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);   //find if id exist in table
        List<Question> questionsFromDB = quiz.get().getQuestions();     //get the questions from quiz id, if id matched

        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for (Question q : questionsFromDB) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUser.add(qw);

        }

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);

    }

    //get the count of right answer.
    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get();           //get the quiz by id from db and save into model
        List<Question> questions = quiz.getQuestions();     //get the questions from quiz, to get right answer from db
        // Responses = user submited answer & question have already right answer in db
        int rightanswer = 0;
        int i = 0;
        for (Response response : responses){
            if (response.getResponse().equals(questions.get(i).getRightAnswer()))
                rightanswer++;

        i++;

        }
        return  new ResponseEntity<>(rightanswer, HttpStatus.OK);
    }




}
