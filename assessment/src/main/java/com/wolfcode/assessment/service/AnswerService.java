package com.wolfcode.assessment.service;

import com.wolfcode.assessment.dto.anwers.AnswerRequest;
import com.wolfcode.assessment.dto.anwers.AnswerResponse;
import com.wolfcode.assessment.entity.Answers;
import com.wolfcode.assessment.entity.Questions;
import com.wolfcode.assessment.repository.AnswerRepository;
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
@RequiredArgsConstructor
@Slf4j
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;


    @Transactional
    public void saveAnswers(List<AnswerRequest> answerRequests, String quizCode) {
        List<Answers> result = answerRepository.findByQuestions_QuizCode(quizCode);
        if (result.isEmpty()) {

            Questions question = questionRepository.findByQuizCode(quizCode);
            if (question.getQuizCode().equalsIgnoreCase(quizCode)) {
                List<Answers> answers = answerRequests.stream().map(answerRequest -> Answers.builder()
                        .answerCode(UUID.randomUUID().toString().substring(0, 7))
                        .choice(answerRequest.getChoice())
                        .answer(answerRequest.getAnswer())
                        .questions(question)
                        .build()).collect(Collectors.toList());

                answerRepository.saveAll(answers);
                log.info("saved answers to question: {} ",quizCode.toLowerCase());
            }else throw new RuntimeException("Question not found with id: " + quizCode);


        }else throw  new IllegalArgumentException("This question already has answers");
    }

    public List<AnswerResponse> getAnswerByQuestionCode(String quizCode) {
        Questions result = questionRepository.findByQuizCode(quizCode);
        if (result == null) {
            throw new RuntimeException("Question not found with id: " + quizCode);
        }

        // Use map operation to convert Answer entities to AnswerResponse DTOs
        List<Answers> answers = answerRepository.findByQuestions_QuizCode(quizCode);
        return answers.stream().map(answer -> mapToAnswerResponse(answer)).toList();

    }

    private AnswerResponse mapToAnswerResponse(Answers answer) {
        return AnswerResponse.builder()
                .answerCode(answer.getAnswerCode())
                .choice(answer.getChoice())
                .answer(answer.getAnswer())
                .build();
    }

    public void deleteAnswerByAnswerId(Long answerId) {
        Optional<Answers> answer = answerRepository.findById(answerId);
        if (answer.isPresent()){
            answerRepository.deleteById(answerId);
        }else throw new RuntimeException("Not Found");
    }

    public AnswerResponse getAnswerByAnswerId(Long answerId) {
        Answers answer = answerRepository.findById(answerId).get();
        return AnswerResponse.builder()
                .answerCode(answer.getAnswerCode())
                .choice(answer.getChoice())
                .answer(answer.getAnswer())
                .build();
    }

    @Transactional
    public AnswerResponse updateAnswer(String answerCode, AnswerRequest answerRequest) {
        Answers result = answerRepository.findByAnswerCode(answerCode);
        if (result != null) {
            Answers answers = Answers.builder()
                    .answerCode(answerCode)
                    .choice(answerRequest.getChoice())
                    .answer(answerRequest.getAnswer())
                    .build();
            return AnswerResponse.builder()
                    .answerCode(answerCode)
                    .choice(answers.getChoice())
                    .answer(answers.getAnswer())
                    .build();
        }else throw new IllegalArgumentException("Answer Not found with code: "+ answerCode);
    }

    public AnswerResponse getAnswerByAnswerCode(String answerCode) {
        Answers answer = answerRepository.findByAnswerCode(answerCode);

        return AnswerResponse.builder()
                .answerCode(answerCode)
                .choice(answer.getChoice())
                .answer(answer.getAnswer())
                .build();
    }
}
