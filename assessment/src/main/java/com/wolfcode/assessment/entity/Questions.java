package com.wolfcode.assessment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "questions")
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long questionId;
    @Column(unique = true,nullable = false)
    private String quizCode;
    private String unitCode;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String question;
    private Integer score;
    @Enumerated(value = EnumType.STRING)
    private AnswerChoice correctAnswer;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "tests", referencedColumnName = "testTitle")
    private CatsAndTests tests;

    @OneToMany(mappedBy = "questions", fetch = FetchType.EAGER)
    private List<Answers> answers;


}
