package ru.eltex.accountingsystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.eltex.accountingsystem.documents.Student;
import ru.eltex.accountingsystem.enums.Role;

import java.util.ArrayList;

@SpringBootApplication // автоконфигурирование, обозначение точки входа
public class Registration {
    @Bean // компонент контекста Spring
    public CommandLineRunner demo(UserRepository userRepository) {
        return (args) -> {
            ArrayList<TestResult> testResults = new ArrayList<>();
            testResults.add(new TestResult("math", 22));
            userRepository.save(new Student("login", "password",
                    "email", "fio", Role.STUDENT, 23, testResults));

            for (User user : userRepository.findAll()) {
                System.out.println(user.getFio());
            }
        };
    }
}

