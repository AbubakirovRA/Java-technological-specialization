package gb.ru.task2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class StudentApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(StudentService studentService) {
        return args -> {
            // Создайте 5-10 студентов и сохраните их в базу данных
            Arrays.asList(
                new Student("Aleksandr Smirnov", "Ekonomika i upravlenie"),
                new Student("Ekaterina Ivanova", "Meditsina i zdravookhranenie"),
                new Student("Dmitriy Kuznetsov", "Iskusstvo i kultura"),
                new Student("Anna Popova", "Informatsionnye tekhnologii"),
                new Student("Sergey Fedorov", "Ekonomika i upravlenie"),
                new Student("Mariya Sokolova", "Meditsina i zdravookhranenie"),
                new Student("Ivan Petrov", "Iskusstvo i kultura"),
                new Student("Yelena Semenova", "Informatsionnye tekhnologii"),
                new Student("Aleksey Vasnetsov", "Ekonomika i upravlenie"),
                new Student("Olga Kozlova", "Meditsina i zdravookhranenie")
                    // Добавьте еще студентов
            ).forEach(studentService::createStudent);
        };
    }
}

