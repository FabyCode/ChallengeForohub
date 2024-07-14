package com.alurachallenge.forohub.domain.topic;

import java.time.LocalDateTime;

public record RespuestaTopicDTO(Long id,
                                String title,
                                String message,
                                Status status,
                                Long usuario_Id,
                                String curso,
                                LocalDateTime date) {
    public RespuestaTopicDTO(Topic topicId) {
        this(
                topicId.getId(),
                topicId.getTitle(),
                topicId.getMessage(),
                topicId.getStatus(),
                topicId.getAuthor().getId(),
                topicId.getCourse(),
                topicId.getDate());
    }
}
