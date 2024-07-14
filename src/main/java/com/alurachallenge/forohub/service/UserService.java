package com.alurachallenge.forohub.service;

import com.alurachallenge.forohub.domain.user.RegistroUserDTO;
import com.alurachallenge.forohub.domain.user.User;
import com.alurachallenge.forohub.repository.UserRepository;
import com.alurachallenge.forohub.domain.user.UserUpdateDTO;
import com.alurachallenge.forohub.infra.errors.ValidacionIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public RegistroUserDTO registrarUsuario(RegistroUserDTO registroUsuarioDTO) {
        if (userRepository.existsByEmail(registroUsuarioDTO.email())) {
            throw new ValidacionIntegridad("Este correo electrónico ya está registrado.");
        }

        if (userRepository.existsByUsername(registroUsuarioDTO.username())) {
            throw new ValidacionIntegridad("Este nombre de usuario ya está en uso.");
        }

        var usuario = new User(registroUsuarioDTO, passwordEncoder);
        userRepository.save(usuario);
        return new RegistroUserDTO(
                usuario.getId(),
                usuario.getName(),
                usuario.getEmail(),
                usuario.getUsername(),
                usuario.getPassword());
    }

    public RegistroUserDTO actualizacionUsuario(Long id, UserUpdateDTO userUpdateDTO) {
        var usuarioOptional = userRepository.findById(id);
        if (usuarioOptional.isEmpty()) {
            throw new ValidacionIntegridad("El usuario con el ID proporcionado no existe.");
        }

        var usuario = usuarioOptional.get();

        usuario.UserUpdateDTO(userUpdateDTO);

        userRepository.save(usuario);
        return new RegistroUserDTO(
                usuario.getId(),
                usuario.getName(),
                usuario.getEmail(),
                usuario.getUsername(),
                usuario.getPassword());
    }
    public RegistroUserDTO desactivarUser(Long id) {
        var usuarioOptional = userRepository.findById(id);
        if (usuarioOptional.isEmpty()) {
            throw new ValidacionIntegridad("El usuario con el ID proporcionado no existe.");
        }

        var usuario = usuarioOptional.get();
        usuario.deactivateUser();

        userRepository.save(usuario);
        return new RegistroUserDTO(
                usuario.getId(),
                usuario.getName(),
                usuario.getEmail(),
                usuario.getUsername(),
                usuario.getPassword());
    }
}
