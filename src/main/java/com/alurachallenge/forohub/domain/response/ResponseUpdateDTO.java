package com.alurachallenge.forohub.domain.response;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ResponseUpdateDTO(
        @NotNull Long id,
        String solution,
        @NotNull Long usuario_Id,
        @NotNull Long topic_Id,
        LocalDateTime creationDate
) {
}
