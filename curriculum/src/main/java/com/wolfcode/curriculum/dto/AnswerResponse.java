package com.wolfcode.curriculum.dto;

import com.wolfcode.curriculum.entity.AnswerChoice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerResponse {

    private String answerCode;
    private AnswerChoice choice;
    private String answer;

}
