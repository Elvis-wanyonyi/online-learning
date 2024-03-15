package com.wolfcode.curriculum.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContentResponse {

    private String contentCode;
    private String instructor;
    private String unitCode;
    private String instructions;
    private String content;
}
