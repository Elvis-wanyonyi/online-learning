package com.wolfcode.curriculum.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponse {

    private String courseName;
    private String description;
    private Integer duration;
    private String courseCode;

    private List<CourseStudents> students;

}
