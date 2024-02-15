package ru.gb.SpringAOP;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Конфигурационный класс Spring.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "ru.gb.SpringAOP")
public class AppConfig {

}

