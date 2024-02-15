package ru.gb.SpringAOP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Главный класс приложения Spring Boot.
 */
@SpringBootApplication
public class SpringAopApplication {

	/**
	 * Метод main для запуска приложения.
	 *
	 * @param args аргументы командной строки
	 */
	public static void main(String[] args) {
		SpringApplication.run(AppConfig.class, args);
	}
}

