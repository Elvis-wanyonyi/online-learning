package com.wolfcode.assessment.dto;

import com.wolfcode.assessment.dto.anwers.AnswerResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizResponse {

//    private String testTitle;
    private String quizCode;
    private String unitCode;
    private String question;
    private Integer score;

    private List<AnswerResponse> answers;

}
