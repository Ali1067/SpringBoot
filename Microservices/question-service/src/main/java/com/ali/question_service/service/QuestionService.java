package com.ali.question_service.service;


import com.ali.question_service.dao.QuestionDao;
import com.ali.question_service.model.Question;
import com.ali.question_service.model.QuestionWrapper;
import com.ali.question_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {


    @Autowired
    QuestionDao questionDao;


    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try{
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK) ;
    }catch (Exception e){
        e.printStackTrace();
    }
        return  new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try{
            questionDao.save(question);
            return new ResponseEntity<>("success", HttpStatus.CREATED) ;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Not Created", HttpStatus.CONFLICT) ;


    }

    public ResponseEntity<List<Integer>> getQuestionsForQuizk(String categoryName, Integer numQuestions) {
    List<Integer> questions = questionDao.findRandomQuestionsByCategory(categoryName,numQuestions);
    return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    //a service will request for the getQuestions
    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionId){
        List<QuestionWrapper> wrapper = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for (Integer id: questionId){
            questions.add(questionDao.findById(id).get());
        }

        for (Question question : questions){
            QuestionWrapper wrapper1 = new QuestionWrapper();
            wrapper1.setId(question.getId());
            wrapper1.setQuestionTitle(question.getQuestionTitle());

            wrapper1.setOption1(question.getOption1());
            wrapper1.setOption2(question.getOption2());
            wrapper1.setOption3(question.getOption3());
            wrapper1.setOption4(question.getOption4());
            wrapper.add(wrapper1);

        }


        return new ResponseEntity<>(wrapper, HttpStatus.OK);
    }


    //ResponseEntity is provide Server codes like 200,400,500
    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int right = 0;

        for (Response response : responses){
            Question question = questionDao.findById(response.getId()).get();
            if (response.getResponse().equals(question.getRightAnswer())){
                right++;
            }


        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
