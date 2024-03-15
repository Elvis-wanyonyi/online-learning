package com.wolfcode.curriculum.dto.units;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestResponse {

    private String testCode;
    private String testTitle;
    private String unitCode;
    private Integer totalScore;

    private List<QuizResponse> questions;
}
