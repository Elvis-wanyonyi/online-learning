package com.wolfcode.content.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContentRequest {

    private String courseCode;
    private String instructor;
    private String unitCode;
    private String instructions;
    private String fileName;
//    private byte[] content;


}
