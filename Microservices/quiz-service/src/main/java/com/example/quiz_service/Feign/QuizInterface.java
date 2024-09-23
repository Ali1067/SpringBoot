package com.example.quiz_service.Feign;

import com.example.quiz_service.model.QuestionWrapper;
import com.example.quiz_service.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/*
* We require a way to search for all microservices from the eureka server. and there're several ways
* 1- we use the IP of each service and register it on every other microservice for communication,
* which is not possilbe because API gateway/ Loadbalancer will always redirect as per load.
*
* the other way is to use Feign, it'll register all of our microservices to Eureka server, instead
* of with IP it'll use the name of service. */
@FeignClient("question-service")
public interface QuizInterface {

    @GetMapping("question/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String  categoryName, @RequestParam Integer numQuestions);

    @PostMapping("question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestiosnFromIid(@RequestBody List<Integer> questionsIds);

    @PostMapping("question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);

}
