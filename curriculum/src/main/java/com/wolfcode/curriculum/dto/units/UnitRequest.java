package com.wolfcode.curriculum.dto.units;

import com.wolfcode.curriculum.entity.Semester;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnitRequest {

    private String unitCode;
    private String unitName;
    private String instructor;
    private Semester semester;

}
