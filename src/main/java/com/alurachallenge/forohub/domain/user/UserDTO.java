package com.alurachallenge.forohub.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDTO(
    @NotBlank(message = "Utilice su correo electrónico como nombre de usuario")
    @Email(message = "Correo electrónico inválido.")
    String email,

    @NotBlank(message = "Debe tener entre 10 y 15 caracteres.")
    String password)
    {
}
