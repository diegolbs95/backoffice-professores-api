package com.backoffice.professores;

import com.backoffice.professores.infra.factory.ProfessorFactory;
import com.backoffice.professores.infra.persistencia.domain.Professor;
import com.backoffice.professores.usercase.service.ProfessorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.backoffice.professores.infra.persistencia.enums.Role.ADMIN;
import static com.backoffice.professores.infra.persistencia.enums.StatusProfessor.APROVADO;

@Slf4j
@SpringBootApplication
public class ProfessoresApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfessoresApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ProfessorService service) {
		return args -> {
			var admin = Professor.builder()
					.email("diego-luis@hotmail.com")
					.nome("Diego Luis")
					.role(ADMIN)
					.status(APROVADO)
					.senha("123456")
					.build();


			log.info("Admin token: " + service.registro(ProfessorFactory.convertProfessorDTO(admin)));
		};
	}
}
