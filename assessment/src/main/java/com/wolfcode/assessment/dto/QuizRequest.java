package com.wolfcode.assessment.dto;

import com.wolfcode.assessment.entity.AnswerChoice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizRequest {

    private String unitCode;
    private String question;
    private Integer score;
    private AnswerChoice correctAnswer;

}
