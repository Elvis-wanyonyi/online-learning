package com.wolfcode.assessment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name =  "answers")
public class Answers {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String answerCode;
    @Column(nullable = false,columnDefinition = "TEXT")
    private String answer;
    @Enumerated(value = EnumType.STRING)
    private AnswerChoice choice;


    @ManyToOne( cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "quiz_code", referencedColumnName = "quizCode")
    private Questions questions;


}
