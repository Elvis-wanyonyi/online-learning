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
@Table(name =  "assessment_tests")
public class CatsAndTests {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String testCode;
    @Column(unique = true,length = 10,nullable = false)
    private String testTitle;
    private String unitCode;
    @Column(columnDefinition = "TEXT")
    private String instructions;
    private Integer totalScore;

    @OneToMany(mappedBy = "tests")
    private List<Questions> questions;
}
