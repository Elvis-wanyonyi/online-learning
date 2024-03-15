package com.wolfcode.curriculum.dto;

import com.wolfcode.curriculum.dto.units.UnitsResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDetails {

    private String courseName;
    private String description;
    private Integer duration;
    private String courseCode;

    private List<UnitsResponse> units;
}
