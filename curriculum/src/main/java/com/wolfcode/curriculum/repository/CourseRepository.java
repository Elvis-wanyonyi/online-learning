package com.wolfcode.curriculum.repository;

import com.wolfcode.curriculum.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Courses, Long> {
    Courses findByCourseName(String courseName);

    Courses deleteByCourseCode(String courseCode);

    Courses findByCourseCode(String courseCode);
}
