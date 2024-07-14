package com.alurachallenge.forohub.controller;

import com.alurachallenge.forohub.domain.user.RegistroUserDTO;
import com.alurachallenge.forohub.domain.user.RespuestaUserDTO;
import com.alurachallenge.forohub.service.UserService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/registro")
public class RegistroController {
    @Autowired
    private UserService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> registrarUsuario(@RequestBody @Valid RegistroUserDTO registroUsuarioDTO, UriComponentsBuilder uriComponentsBuilder) {
        try {
            RegistroUserDTO usuario = usuarioService.registrarUsuario(registroUsuarioDTO);
            RespuestaUserDTO respuestaUsuarioDTO;
            respuestaUsuarioDTO = new RespuestaUserDTO(usuario.getId(), usuario.getName());
            URI url = uriComponentsBuilder.path("usuario/{id}").buildAndExpand(usuario.getId()).toUri();
            return ResponseEntity.created(url).body(respuestaUsuarioDTO);
        } catch (ConstraintViolationException ex) {
            return ResponseEntity.badRequest().body("Validation failed: " + ex.getMessage());
        }
    }
}
