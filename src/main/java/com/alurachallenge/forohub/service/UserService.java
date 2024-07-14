package com.alurachallenge.forohub.service;

import com.alurachallenge.forohub.domain.user.RegistroUserDTO;
import com.alurachallenge.forohub.domain.user.User;
import com.alurachallenge.forohub.domain.user.UserUpdateDTO;
import com.alurachallenge.forohub.repository.UserRepository;
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
        // Verificar si el correo electrónico ya está registrado en la base de datos
        if (userRepository.existsByEmail(registroUsuarioDTO.email())) {
            throw new ValidacionDeIntegridad("Este correo electrónico ya está registrado.");
        }

        // Verificar si el nombre de usuario ya está en uso
        if (userRepository.existsByUsername(registroUsuarioDTO.username())) {
            throw new ValidacionDeIntegridad("Este nombre de usuario ya está en uso.");
        }

        // Crear un nuevo usuario y cifrar la contraseña
        var usuario = new User(registroUsuarioDTO, passwordEncoder);
        userRepository.save(usuario);
        return new RegistroUserDTO(
                usuario.getId(),
                usuario.getName(),
                usuario.getEmail(),
                usuario.getUsername(),
                usuario.getPassword());
    }

    public RegistroUserDTO actualizacionUsuario(Long id, UserUpdateDTO actualizacionUsuarioDTO) {
        var usuarioOptional = userRepository.findById(id);
        if (usuarioOptional.isEmpty()) {
            throw new ValidacionDeIntegridad("El usuario con el ID proporcionado no existe.");
        }

        var usuario = usuarioOptional.get();

        // Actualizar los campos del usuario si se proporcionan nuevos valores
        usuario.UserUpdateDTO(UserUpdateDTO);

        /*// Si la contraseña ha cambiado, cifrarla nuevamente
        if (!actualizacionUsuarioDTO.password().isEmpty()) {
            usuario.setPassword(passwordEncoder.encode(actualizacionUsuarioDTO.password()));
        }*/

        // Guardar el usuario actualizado en la base de datos
        userRepository.save(usuario);
        return new RegistroUserDTO(
                usuario.getId(),
                usuario.getName(),
                usuario.getEmail(),
                usuario.getUsername(),
                usuario.getPassword());
    }
    public RegistroUserDTO desactivarUser(Long id) {
        // Verificar si el usuario existe en la base de datos
        var usuarioOptional = userRepository.findById(id);
        if (usuarioOptional.isEmpty()) {
            throw new ValidacionDeIntegridad("El usuario con el ID proporcionado no existe.");
        }

        var usuario = usuarioOptional.get();

        // Desactivar el usuario
        usuario.deactivateUser();

        // Guardar el usuario desactivado en la base de datos
        userRepository.save(usuario);
        return new RegistroUserDTO(
                usuario.getId(),
                usuario.getName(),
                usuario.getEmail(),
                usuario.getUsername(),
                usuario.getPassword());
    }
}
