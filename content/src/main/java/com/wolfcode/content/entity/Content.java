package com.wolfcode.content.entity;

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
@Table(name = "content")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String contentCode;
    private String instructor;
    //@Column(nullable = false)
    private String unitCode;
    @Column(columnDefinition = "LONGTEXT")
    private String instructions;
    @Lob
    @Column(columnDefinition = "LONGBLOB",nullable = false)
    private byte[] content;
}
