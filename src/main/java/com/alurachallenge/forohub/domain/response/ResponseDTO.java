package com.alurachallenge.forohub.domain.response;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ResponseDTO(
        @NotBlank
        String solution,
        @NotNull
        @Valid
        Long user_Id,
        @NotNull
        @Valid
        Long topic_Id,
        LocalDateTime creationDate) {
}
