package com.wolfcode.curriculum.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "units")
public class Units {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String unitCode;
    private String unitId;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String unitName;
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Semester semester;
    private String instructor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_code", referencedColumnName = "courseCode")
    private Courses courses;
}
