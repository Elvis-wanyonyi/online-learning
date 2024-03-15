package com.wolfcode.assessment.service;

import com.wolfcode.assessment.dto.QuizRequest;
import com.wolfcode.assessment.dto.QuizResponse;
import com.wolfcode.assessment.dto.anwers.AnswerResponse;
import com.wolfcode.assessment.entity.Answers;
import com.wolfcode.assessment.entity.CatsAndTests;
import com.wolfcode.assessment.entity.Questions;
import com.wolfcode.assessment.repository.AssessTestRepository;
import com.wolfcode.assessment.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    private final AssessTestRepository assessTestRepository;


    @Transactional
    public void createQuiz(QuizRequest quizRequest, String testTitle) {
        CatsAndTests tests = assessTestRepository.findByTestTitle(testTitle);

        if (tests.getTestTitle().equalsIgnoreCase(testTitle)) {
            Questions question = Questions.builder()
                    .quizCode(UUID.randomUUID().toString().toUpperCase().substring(0, 6))
                    .unitCode(quizRequest.getUnitCode())
                    .question(quizRequest.getQuestion())
                    .score(quizRequest.getScore())
                    .tests(tests)
                    .correctAnswer(quizRequest.getCorrectAnswer())
                    .build();
            questionRepository.save(question);
            log.info("Created new question: {} for Unit: {}", question.getQuizCode(), question.getUnitCode());

//            return QuizResponse.builder()
//                    .unitCode(question.getUnitCode())
//                    .quizCode(question.getQuizCode())
//                    .question(question.getQuestion())
//                    .score(question.getScore())
//                    .build();
        }else throw new RuntimeException("no such title");
    }

    public Optional<Questions> getQuestionById(Long questionId) {
        Optional<Questions> question = questionRepository.findById(questionId);
        return question;

    }

    public List<QuizResponse> getQuestionByUnitCode(String unitCode) {
        List<Questions> questions = questionRepository.findByUnitCode(unitCode);
        List<Questions> questionsWithAnswers = questionRepository.findAllWithEagerRelationships(questions);

        return questionsWithAnswers.stream()
                .map(question -> mapToQuizResponse(question))
                .toList();
    }

    private QuizResponse mapToQuizResponse(Questions question) {
        List<AnswerResponse> responseAnswers = question.getAnswers().stream()
                .map(answers -> new AnswerResponse(answers.getAnswerCode(),
                        answers.getChoice(),
                        answers.getAnswer()))
                .collect(Collectors.toList());

        return QuizResponse.builder()
                .quizCode(question.getQuizCode())
                .unitCode(question.getUnitCode())
                .question(question.getQuestion())
                .score(question.getScore())
                .answers(responseAnswers)
                .build();
    }

    public QuizResponse getQuestionByQuizCode(String quizCode) {
        Questions question = questionRepository.findByQuizCode(quizCode);

        return QuizResponse.builder()
                .unitCode(question.getUnitCode())
                .quizCode(question.getQuizCode())
                .question(question.getQuestion())
                .score(question.getScore())
                .build();
    }

    public void deleteByQuizCode(String quizCode) {
        Questions question = questionRepository.deleteByQuizCode(quizCode);
    }

    public QuizResponse getQuizWithAnswersById(Long questionId) {
        Questions question = questionRepository.findById(questionId).get();
        List<Answers> answersList = question.getAnswers();

        List<AnswerResponse> responseAnswers = answersList.stream()
                .map(answers -> new AnswerResponse(answers.getAnswerCode(),
                        answers.getChoice(), answers.getAnswer())).collect(Collectors.toList());


        return QuizResponse.builder()
                .quizCode(question.getQuizCode())
                .unitCode(question.getUnitCode())
                .question(question.getQuestion())
                .score(question.getScore())
                .answers(responseAnswers)
                .build();
    }


    public List<QuizResponse> getQuestionByTestTitle(String testTitle) {
        List<Questions> questions = questionRepository.findQuestionByTests_TestTitle(testTitle);
        return questions.stream().map(question -> mapToQuizResponse(question)).toList();
    }
}
