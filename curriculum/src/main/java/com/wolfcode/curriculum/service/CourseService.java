package com.wolfcode.curriculum.service;

import com.wolfcode.curriculum.dto.CourseDetails;
import com.wolfcode.curriculum.dto.CourseRequest;
import com.wolfcode.curriculum.dto.CourseResponse;
import com.wolfcode.curriculum.dto.units.UnitsResponse;
import com.wolfcode.curriculum.entity.Courses;
import com.wolfcode.curriculum.repository.CourseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseService {

    private final CourseRepository courseRepository;
    private final UnitService unitService;


    @Transactional
    public void createCourse(CourseRequest courseRequest) {

        Courses courses = Courses.builder()
                .courseName(courseRequest.getCourseName().toUpperCase())
                .courseCode(courseRequest.getCourseCode().toUpperCase())
                .courseUid(UUID.randomUUID().toString().toUpperCase().substring(0, 4))
                .description(courseRequest.getDescription())
                .duration(courseRequest.getDuration())
                .build();

        courseRepository.save(courses);
        log.info("new course {} {} was added .", courses.getCourseCode(), courses.getCourseName());
    }

    public List<CourseResponse> getAllCourses() {
        List<Courses> courses = courseRepository.findAll();
        return courses.stream().map(this::mapToCourseResponse).toList();
    }

    private CourseResponse mapToCourseResponse(Courses course) {

        return CourseResponse.builder()
                .courseCode(course.getCourseCode())
                .description(course.getDescription())
                .courseName(course.getCourseName())
                .duration(course.getDuration())
                .build();
    }

    public CourseResponse getCourseByName(String courseName) {
        Courses course = courseRepository.findByCourseName(courseName);
        return CourseResponse.builder()
                .courseCode(course.getCourseCode())
                .courseName(course.getCourseName())
                .duration(course.getDuration())
                .description(course.getDescription())
                .build();
    }

    public Optional<Courses> getCourseById(Long courseId) {
        return courseRepository.findById(courseId);
    }

    @Transactional
    public void deleteByCourseCode(String courseCode) {

        Courses course = courseRepository.findByCourseCode(courseCode);
        if (course != null) {
            courseRepository.deleteByCourseCode(courseCode);
            log.info("course: {} has been deleted from the curriculum", courseCode);

        } else throw new IllegalArgumentException("Course Code Doesn't Exist");
        log.error("course code: {} not found ", courseCode);

    }

    public CourseDetails getCourseByCourseCode(String courseCode) {
        Courses course = courseRepository.findByCourseCode(courseCode);
        List<UnitsResponse> units = unitService.getUnitByCourseCode(courseCode);
        return CourseDetails.builder()
                .courseCode(courseCode.toUpperCase())
                .courseName(course.getCourseName().toUpperCase())
                .description(course.getDescription())
                .duration(course.getDuration())
                .units(units)
                .build();
    }

}
