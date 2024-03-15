package com.wolfcode.curriculum.repository;

import com.wolfcode.curriculum.entity.Units;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitsRepository extends JpaRepository<Units, Long> {


    Units findByUnitName(String name);

    Units findByUnitCode(String unitCode);

//    List<Units> findByCourseCode(String courseCode);

    List<Units> findByInstructor(String instructor);

    @Transactional
    void deleteByUnitCode(String unitCode);

    List<Units> findByCourses_CourseCode(String courseCode);

    Units findUnitByUnitCode(String unitCode);

}
