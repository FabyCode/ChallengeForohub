package com.alurachallenge.forohub.domain.topic;

import java.time.LocalDateTime;

public record ListarTopicsDTO(
        Long id,
        String title,
        String message,
        Status status,
        Long usuario_Id,
        String curso,
        LocalDateTime date
) {
    public ListarTopicsDTO (Topic topic){
        this(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getStatus(),
                topic.getAuthor().getId(),
                topic.getCourse(),
                topic.getDate());

    }
}
