package com.wolfcode.content.repository;

import com.wolfcode.content.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {

    List<Content> findByUnitCode(String unitCode);

    List<Content> findByInstructor(String instructor);

    Content deleteByContentCode(String contentCode);
}
