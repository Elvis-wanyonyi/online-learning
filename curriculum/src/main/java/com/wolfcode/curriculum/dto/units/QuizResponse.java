package com.wolfcode.curriculum.dto.units;


import com.wolfcode.curriculum.dto.AnswerResponse;
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

    private String quizCode;
    private String unitCode;
    private String question;
    private Integer score;
    private List<AnswerResponse> answers;

}
