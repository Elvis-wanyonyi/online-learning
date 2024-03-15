package com.wolfcode.curriculum.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "courses")
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long courseId;
    private String courseUid;
    private String courseName;
    private String description;
    private Integer duration;
    @Column(unique = true)
    private String courseCode;

    @OneToMany(mappedBy = "courses", fetch = FetchType.EAGER)
    private List<Units> units;
}
