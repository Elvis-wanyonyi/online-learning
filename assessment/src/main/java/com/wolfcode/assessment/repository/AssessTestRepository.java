package com.wolfcode.assessment.repository;

import com.wolfcode.assessment.entity.CatsAndTests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssessTestRepository extends JpaRepository<CatsAndTests,Long> {
    List<CatsAndTests> findByUnitCode(String unitCode);

    CatsAndTests findByTestTitle(String testTitle);
}
