package com.wolfcode.curriculum.dto.units;

import com.wolfcode.curriculum.dto.ContentResponse;
import com.wolfcode.curriculum.entity.Semester;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnitMaterials {

    private String unitCode;
    private String unitName;
    private String instructor;
    private Semester semester;

    private List<ContentResponse> resources;
}
