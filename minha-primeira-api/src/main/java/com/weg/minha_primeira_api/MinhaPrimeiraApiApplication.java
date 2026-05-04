package com.weg.minha_primeira_api;

import java.sql.SQLException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.weg.minha_primeira_api.connection.ConnectionFactory;

@SpringBootApplication
public class MinhaPrimeiraApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinhaPrimeiraApiApplication.class, args);
	}

	@Bean
	CommandLineRunner runner () {
		return args -> {
			try {
				ConnectionFactory.initializeDatabase();
				System.out.println("Banco de dados inicializado com sucesso");
			}
			catch (SQLException e) {
				System.out.println("Erro ao iniacializar o banco de dados" + e.getMessage());
			}
		};
	}
}
