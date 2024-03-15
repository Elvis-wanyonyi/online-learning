package com.wolfcode.assessment.repository;

import com.wolfcode.assessment.entity.CatsAndTests;
import com.wolfcode.assessment.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Questions,Long> {
    List<Questions> findByUnitCode(String unitCode);

    Questions findByQuizCode(String quizCode);

    Questions deleteByQuizCode(String quizCode);


    @Query("SELECT q FROM Questions q LEFT JOIN FETCH q.answers")
    List<Questions> findAllWithEagerRelationships(List<Questions> questions);


    CatsAndTests findByTests_TestTitle(String testTitle);

    List<Questions> findQuestionByTests_TestTitle(String testTitle);
}
