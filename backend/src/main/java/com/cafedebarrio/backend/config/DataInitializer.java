package com.cafedebarrio.backend.config;

import com.cafedebarrio.backend.entity.Usuario;
import com.cafedebarrio.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    @Value("${app.admin.username}")
    private String adminUsername;

    @Value("${app.admin.password}")
    private String adminPassword;

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.findByUsername(adminUsername).isEmpty()) {
            Usuario admin = Usuario.builder()
                .username(adminUsername)
                .password(passwordEncoder.encode(adminPassword))
                .rol("ADMIN")
                .build();

            usuarioRepository.save(admin);
        }
    }
}