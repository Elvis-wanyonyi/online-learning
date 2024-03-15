package com.wolfcode.assessment.controller;

import com.wolfcode.assessment.dto.QuizRequest;
import com.wolfcode.assessment.dto.QuizResponse;
import com.wolfcode.assessment.entity.Questions;
import com.wolfcode.assessment.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/assess/questions/")
public class QuestionController {

    private final QuestionService questionService;


    @PostMapping("{testTitle}/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String createQuiz(@RequestBody QuizRequest quizRequest,@PathVariable String testTitle) {
        questionService.createQuiz(quizRequest, testTitle);
        return "Question Created !!, add answer choices to it .";
    }

    @GetMapping("id/{questionId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Questions> getQuestionById(@PathVariable Long questionId){
        return questionService.getQuestionById(questionId);
    }

    @GetMapping("withAnswers/{questionId}")
    @ResponseStatus(HttpStatus.OK)
    public QuizResponse getQuizWithAnswersById(@PathVariable Long questionId){
        return questionService.getQuizWithAnswersById(questionId);
    }

    @GetMapping("testTitle/{testTitle}")
    @ResponseStatus(HttpStatus.OK)
    public List<QuizResponse> getQuestionByTestTitle(@PathVariable String testTitle){
        return questionService.getQuestionByTestTitle(testTitle);
    }

    @GetMapping("unit/{unitCode}")
    @ResponseStatus(HttpStatus.OK)
    public List<QuizResponse> getQuestionByUnitCode(@PathVariable String unitCode){
        return questionService.getQuestionByUnitCode(unitCode);
    }

    @GetMapping("{quizCode}")
    @ResponseStatus(HttpStatus.OK)
    public QuizResponse getQuestionByQuizCode(@PathVariable String quizCode){
        return questionService.getQuestionByQuizCode(quizCode);
    }

    @DeleteMapping("delete/{quizCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteByQuizCode(@PathVariable("quizCode")String quizCode){
        questionService.deleteByQuizCode(quizCode);
        return "DONE!!";
    }
}
