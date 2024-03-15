package com.wolfcode.curriculum.controller;

import com.wolfcode.curriculum.dto.CourseDetails;
import com.wolfcode.curriculum.dto.CourseRequest;
import com.wolfcode.curriculum.dto.CourseResponse;
import com.wolfcode.curriculum.entity.Courses;
import com.wolfcode.curriculum.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/course")
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/newCourse")
    @ResponseStatus(HttpStatus.CREATED)
    public String createCourse(@RequestBody CourseRequest courseRequest){
        courseService.createCourse(courseRequest);
        return "Course added to school's curriculum";
    }

    @GetMapping("/{courseCode}")
    @ResponseStatus(HttpStatus.OK)
    public CourseDetails getCourseByCourseCode(@PathVariable("courseCode")String courseCode){
        return courseService.getCourseByCourseCode(courseCode);
    }
    @GetMapping("/id/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Courses> getCourseById(@PathVariable("courseId")Long courseId){

        return courseService.getCourseById(courseId);
    }
    @DeleteMapping("/deleteCourse/{courseCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteByCourseCode(@PathVariable("courseCode")String courseCode){
        courseService.deleteByCourseCode(courseCode);
        return "Course "+ courseCode +" has been deleted";
    }

    @GetMapping("/courses")
    @ResponseStatus(HttpStatus.OK)
    public List<CourseResponse>getAllCourses(){
        return courseService.getAllCourses();
    }

    @GetMapping("/name/{courseName}")
    @ResponseStatus(HttpStatus.OK)
    public CourseResponse getCourseByName(@PathVariable("courseName") String courseName){
        return courseService.getCourseByName(courseName);
    }


}
