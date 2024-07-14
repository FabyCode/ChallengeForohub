package com.alurachallenge.forohub.repository;

import com.alurachallenge.forohub.domain.topic.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    boolean existsByTitleIgnoreCase(String title);
    boolean existsByMessageIgnoreCase(String message);
    Page<Topic> findByActiveTrue(Pageable paged);
}
