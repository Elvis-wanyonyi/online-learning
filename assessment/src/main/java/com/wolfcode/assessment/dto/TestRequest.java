package com.wolfcode.assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestRequest {

    private String testTitle;
    private String instructions;
    private String unitCode;
    private Integer totalScore;

}
