package com.wolfcode.assessment.dto.anwers;

import com.wolfcode.assessment.entity.AnswerChoice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerRequest {

    private String answer;
    private AnswerChoice choice;
}
