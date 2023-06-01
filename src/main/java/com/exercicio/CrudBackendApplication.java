package com.exercicio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.exercicio.model.Setor;
import com.exercicio.repository.SetorRepository;

@SpringBootApplication
public class CrudBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(SetorRepository setorRepository){
		return args -> {
			setorRepository.deleteAll();
			
			Setor s = new Setor();
			s.setName("Desenvolvimento");
			s.setCategory("Pack");
			setorRepository.save(s);
		};
	}
}
