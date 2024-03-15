package com.wolfcode.assessment.controller;

import com.wolfcode.assessment.dto.anwers.AnswerRequest;
import com.wolfcode.assessment.dto.anwers.AnswerResponse;
import com.wolfcode.assessment.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/assess/answers/")
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping("{quizCode}/save")
    public String saveAnswers(@RequestBody List<AnswerRequest> answerRequests, @PathVariable String quizCode) {
        answerService.saveAnswers(answerRequests, quizCode);
        return "Answers saved successfully";
    }

    @GetMapping("{quizCode}")
    public List<AnswerResponse> getAnswerByQuestionId(@PathVariable("quizCode") String quizCode) {
        return answerService.getAnswerByQuestionCode(quizCode);
    }

    @GetMapping("ans/{answerId}")
    public AnswerResponse getAnswerByAnswerId(@PathVariable("answerId") Long answerId) {
        return answerService.getAnswerByAnswerId(answerId);
    }

    @GetMapping("ans/{answerCode}")
    public AnswerResponse getAnswerByAnswerCode(@PathVariable("answerCode") String answerCode) {
        return answerService.getAnswerByAnswerCode(answerCode);
    }

    @DeleteMapping("delete/{quizCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteAnswerByAnswerId(@PathVariable("answerId") Long answerId) {
        answerService.deleteAnswerByAnswerId(answerId);
        return "DONE!!";
    }

    @PutMapping("update/{answerCode}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AnswerResponse updateAnswer(@PathVariable String answerCode, @RequestBody AnswerRequest answerRequest) {
        return answerService.updateAnswer(answerCode, answerRequest);
    }

}
