package com.alurachallenge.forohub.controller;

import com.alurachallenge.forohub.domain.user.*;
import com.alurachallenge.forohub.repository.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@SecurityRequirement(name="bearer-key")
public class UsuarioController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/usuarios")
    public ResponseEntity<Page<ListarUsersDTO>> listarUsuarios(@PageableDefault(size = 10) Pageable paged){
        return ResponseEntity.ok(userRepository.findByActiveTrue(paged).map(ListarUsersDTO::new));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizacionUsuario (@RequestBody @Valid UserUpdateDTO userUpdateDTO){
        User user = userRepository.getReferenceById(userUpdateDTO.id());
        user.UserUpdateDTO(userUpdateDTO);
        return ResponseEntity.ok(new UserUpdateDTO(user.getId(), user.getName(), user.getEmail()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarUsuario(@PathVariable Long id){
        User usuario = userRepository.getReferenceById(id);
        usuario.deactivateUser();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity <RespuestaUserDTO> registrarUsuario(@PathVariable Long id){
        User usuario = userRepository.getReferenceById(id);
        var usuarioDetail = new RespuestaUserDTO(usuario.getId(), usuario.getName());
        return ResponseEntity.ok(usuarioDetail);
    }
}
