package com.cafedebarrio.backend.controller;

import com.cafedebarrio.backend.dto.LoginRequest;
import com.cafedebarrio.backend.dto.LoginResponse;
import com.cafedebarrio.backend.dto.RegistroRequest;
import com.cafedebarrio.backend.entity.Usuario;
import com.cafedebarrio.backend.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        Usuario usuario = usuarioRepository
                .findByUsername(request.getUsername())
                .orElse(null);

        if (usuario == null ||
                !passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {

            return ResponseEntity.status(401)
                    .body(Map.of("error", "Usuario o contraseña incorrectos"));
        }

        return ResponseEntity.ok(
                new LoginResponse(
                        usuario.getId(),
                        usuario.getUsername(),
                        usuario.getRol()
                )
        );
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registro(@RequestBody RegistroRequest request) {

        if (usuarioRepository.findByUsername(request.getUsername()).isPresent()) {

            return ResponseEntity.status(400)
                    .body(Map.of("error", "El nombre de usuario ya está en uso"));
        }

        Usuario nuevo = Usuario.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol("USER")
                .build();

        usuarioRepository.save(nuevo);

        return ResponseEntity.ok(
                Map.of("mensaje", "Usuario registrado correctamente")
        );
    }

}