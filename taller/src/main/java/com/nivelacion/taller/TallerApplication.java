package com.nivelacion.taller;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nivelacion.taller.enums.Role;
import com.nivelacion.taller.models.Usuario;
import com.nivelacion.taller.services.UsuarioService;

@SpringBootApplication
public class TallerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TallerApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEnconder() {
		return new BCryptPasswordEncoder();
	}

	// @Bean
	// CommandLineRunner run(UsuarioService usuarioService) {
	// return args -> {
	// usuarioService.saveUsuario(new Usuario(null, "fabio", "olivera",
	// "fabio@i2t.com.ar", "1234",
	// Collections.singletonList(Role.ROLE_USER)));
	// usuarioService.saveUsuario(new Usuario(null, "mile", "olivera",
	// "mile@i2t.com.ar", "1234",
	// Collections.singletonList(Role.ROLE_ADMIN)));

	// // usuarioService.addRoleToUsuario("fabio@i2t.com.ar", Role.ROLE_USER);
	// // usuarioService.addRoleToUsuario("mile@i2t.com.ar", Role.ROLE_ADMIN);

	// };
	// }

}
