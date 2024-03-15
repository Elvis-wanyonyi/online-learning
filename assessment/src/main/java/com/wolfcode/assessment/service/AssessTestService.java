package com.wolfcode.assessment.service;

import com.wolfcode.assessment.dto.QuizResponse;
import com.wolfcode.assessment.dto.TestAttempt;
import com.wolfcode.assessment.dto.TestRequest;
import com.wolfcode.assessment.dto.TestResponse;
import com.wolfcode.assessment.entity.Answers;
import com.wolfcode.assessment.entity.CatsAndTests;
import com.wolfcode.assessment.entity.Questions;
import com.wolfcode.assessment.repository.AssessTestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssessTestService {

    private final AssessTestRepository assessTestRepository;
    private final QuestionService questionService;

    public List<TestResponse> getAssessmentTest(String unitCode) {
        List<QuizResponse> questions = questionService.getQuestionByUnitCode(unitCode);///???
        List<CatsAndTests> tests = assessTestRepository.findByUnitCode(unitCode);

        return tests.stream().map(test -> mapToTestResponse(test)).toList();
    }

    private TestResponse mapToTestResponse(CatsAndTests test) {
        return TestResponse.builder()
                .testCode(test.getTestCode())
                .testTitle(test.getTestTitle())
                .unitCode(test.getUnitCode())
                .totalScore(test.getTotalScore())
                .questions(questionService.getQuestionByUnitCode(test.getUnitCode()))
                .build();
    }


    public void createAssessTest(TestRequest testRequest) {
        CatsAndTests tests = CatsAndTests.builder()
                .testCode(UUID.randomUUID().toString().toUpperCase().substring(0,4))
                .unitCode(testRequest.getUnitCode())
                .testTitle(testRequest.getTestTitle())
                .instructions(testRequest.getInstructions())
                .totalScore(testRequest.getTotalScore())
                .build();
        assessTestRepository.save(tests);
        log.info("Created new Assessment Test:  {}  {}  {}", tests.getTestCode(), tests.getUnitCode(), tests.getTestTitle());
    }

    public TestResponse getAssessmentTestByTestTitle(String testTitle) {
        CatsAndTests assessment = assessTestRepository.findByTestTitle(testTitle);
        if (assessment != null){
            List<QuizResponse> questions = questionService.getQuestionByTestTitle(testTitle).subList(0,5);
            return TestResponse.builder()
                    .testTitle(assessment.getTestTitle())
                    .testCode(assessment.getTestCode())
                    .unitCode(assessment.getUnitCode())
                    .questions(questions)
                    .totalScore(assessment.getTotalScore())
                    .build();
        }else throw new RuntimeException("Test Not Found");

    }


    public TestAttempt attemptTest(Long id, @RequestParam Map<Long, String> userSelections) {

            CatsAndTests test = assessTestRepository.findById(id).orElseThrow(() -> new RuntimeException("Test not found"));
            List<Questions> questions = test.getQuestions();

            HashMap<Long, String> userAnswers = convertUserSelections(userSelections);
            int totalScore = calculateScore(questions, userAnswers);

            return TestAttempt.builder()
                    .totalScore(totalScore)
                    .build();
    }

    private HashMap<Long, String> convertUserSelections(Map<Long, String> userSelections) {

        HashMap<Long, String> userAnswers = new HashMap<>();
        for (Map.Entry<Long, String> entry : userSelections.entrySet()) {
            Long questionId = Long.parseLong(String.valueOf(entry.getKey()));
            userAnswers.put(questionId, entry.getValue());
        }
        return userAnswers;
    }

    private int calculateScore(List<Questions> questions, HashMap<Long, String> userAnswers) {
        int totalScore = 0;
        for (Questions question : questions) {
            String userSelection = userAnswers.get(question.getQuestionId());
            if (userSelection != null && validateAnswer(question, userSelection)) {
                totalScore += question.getScore();
            }
        }
        return totalScore;
    }

    private boolean validateAnswer(Questions question, String userSelection) {
        for (Answers answer : question.getAnswers()) {
            if (answer.getChoice().name().equals(userSelection) && answer.getChoice() == question.getCorrectAnswer()) {
                return true;
            }
        }
        return false;
    }

}
