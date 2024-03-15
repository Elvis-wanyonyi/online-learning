package com.wolfcode.assessment.repository;

import com.wolfcode.assessment.entity.Answers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answers, Long> {
    List<Answers> findByQuestions_QuestionId(Long questionId);

    Answers findByAnswerCode(String answerCode);

    List<Answers> findByQuestions_QuizCode(String quizCode);
}
