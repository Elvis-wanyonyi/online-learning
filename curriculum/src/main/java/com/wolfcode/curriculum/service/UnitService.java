package com.wolfcode.curriculum.service;

import com.wolfcode.curriculum.dto.ContentResponse;
import com.wolfcode.curriculum.dto.units.*;
import com.wolfcode.curriculum.entity.Courses;
import com.wolfcode.curriculum.entity.Units;
import com.wolfcode.curriculum.feignClients.AssessmentClient;
import com.wolfcode.curriculum.feignClients.ContentClient;
import com.wolfcode.curriculum.repository.CourseRepository;
import com.wolfcode.curriculum.repository.UnitsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UnitService {

    private final UnitsRepository unitsRepository;
    private final ContentClient contentClient;
    private final AssessmentClient assessmentClient;
    private final CourseRepository courseRepository;


    @Transactional
    public void addUnit(List<UnitRequest> unitRequest, String courseCode) {
        Courses courses = courseRepository.findByCourseCode(courseCode);

        if (courses.getCourseCode().equalsIgnoreCase(courseCode)) {
            List<Units> units = unitRequest.stream().map(unitRequests -> Units.builder()
                    .unitId(UUID.randomUUID().toString().toUpperCase().substring(0, 7))
                    .unitCode(unitRequests.getUnitCode().toUpperCase())
                    .unitName(unitRequests.getUnitName())
                    .semester(unitRequests.getSemester())
                    .instructor(unitRequests.getInstructor().toUpperCase())
                    .courses(courses)
                    .build()).collect(Collectors.toList());

            unitsRepository.saveAll(units);
            log.info("new unit: was added to course: {}  ",courses.getCourseCode());
        }else throw new RuntimeException("course not found  " + courseCode);

    }


    public UnitsResponse getUnitByName(String name) {
        Units unit = unitsRepository.findByUnitName(name);
        return UnitsResponse.builder()

                .unitCode(unit.getUnitCode())
                .unitName(unit.getUnitName())
                .instructor(unit.getInstructor())
                .build();
    }

    public UnitsResponse getUnitByUnitCode(String unitCode) {
        Units unit = unitsRepository.findUnitByUnitCode(unitCode);
        return UnitsResponse.builder()
                .unitCode(unit.getUnitCode())
                .unitName(unit.getUnitName())
                .instructor(unit.getInstructor())
                .build();
    }

    public List<UnitsResponse> getUnitByInstructor(String instructor) {
        List<Units> units = unitsRepository.findByInstructor(instructor);
        return units.stream().map(unit -> mapToUnitResponse(unit)).toList();
    }

    private UnitsResponse mapToUnitResponse(Units unit) {
        return UnitsResponse.builder()
                .unitCode(unit.getUnitCode())
                .unitName(unit.getUnitName())
                .instructor(unit.getInstructor())
                .build();
    }

    public void deleteByUnitCode(String unitCode) {
        Units units = unitsRepository.findUnitByUnitCode(unitCode);
        if (units != null) {
            unitsRepository.deleteByUnitCode(unitCode);
            log.info("unit: {} has been deleted",unitCode);
        } else throw new IllegalArgumentException("unit not found");
        log.error("unit not found");
    }

    public UnitsResponse updateUnitDetails(Long unitId, UnitRequest unitRequest) {
        Optional<Units> units = unitsRepository.findById(unitId);
        if (units.isPresent()) {
            Units unit = Units.builder()
                    .unitCode(unitRequest.getUnitCode())
                    .unitName(unitRequest.getUnitName())
                    .instructor(unitRequest.getInstructor())
                    .build();
            unitsRepository.save(unit);
            log.info("Unit: {} has been updated to {}  {} ",unitId, unit.getUnitCode(), unit.getUnitName());
            return UnitsResponse.builder()
                    .unitCode(unit.getUnitCode())
                    .unitName(unit.getUnitName())
                    .instructor(unit.getInstructor())
                    .build();
        } else throw new RuntimeException("Unit Not Found");
    }

//    public List<UnitsResponse> getUnitByCourseId(Long courseId) {
//        List<Units> unit = unitsRepository.findByCourses_CourseId(courseId);
//        return unit.stream().map(units -> mapToResponse(units) ).toList();
//    }

    private UnitsResponse mapToResponse(Units units) {
        return UnitsResponse.builder()
                //.courses(units.getCourses())
                .unitCode(units.getUnitCode())
                .unitName(units.getUnitName())
                .instructor(units.getInstructor())
                .semester(units.getSemester())
                .build();
    }

    public UnitMaterials getUnitResources(String unitCode) {
        Units unit = unitsRepository.findByUnitCode(unitCode);
        List<ContentResponse> resources = contentClient.getContentByUnitCode(unitCode);
        return UnitMaterials.builder()
                //.courses(units.getCourses())
                .unitCode(unit.getUnitCode())
                .unitCode(unit.getUnitCode())
                .unitName(unit.getUnitName())
                .instructor(unit.getInstructor())
                .resources(resources)
                .build();
    }
//
//    private UnitMaterials mapToUnitMaterials(Units units) {
//        List<ContentResponse> resources = contentClient.getContentByUnitCode(units.getUnitCode());
//        return UnitMaterials.builder()
//                //.courses(units.getCourses())
//                .unitCode(units.getUnitCode())
//                .unitCode(units.getUnitCode())
//                .unitName(units.getUnitName())
//                .instructor(units.getInstructor())
//                .resources(resources)
//                .build();
//    }

    public UnitAssessments getUnitUnitAssessments(String unitCode) {
        Units unit = unitsRepository.findByUnitCode(unitCode);
        List<TestResponse> assessment =assessmentClient.getAssessmentTest(unitCode);

        return UnitAssessments.builder()
                .unitCode(unit.getUnitCode())
                .unitName(unit.getUnitName())
                .semester(unit.getSemester())
                .instructor(unit.getInstructor())
                .assessTests(assessment)
                .build();
    }

    public List<UnitsResponse> getUnitByCourseCode(String courseCode) {
        List<Units> units = unitsRepository.findByCourses_CourseCode(courseCode);
        return units.stream().map(unit -> mapToUnitResponse(unit)).toList();
    }
}
