package com.Literalura.Literalura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Autowired
	private DataSource dataSource;

	@Override
	public void run(String... args) {
		try (Connection connection = dataSource.getConnection()) {
			System.out.println("Conexión exitosa a la base de datos: " + connection.getMetaData().getDatabaseProductName());
		} catch (SQLException e) {
			System.err.println("Error al conectar a la base de datos: " + e.getMessage());
		}
	}
}



