package ru.eltex.accountsystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.eltex.accountsystem.dao.UserRepository;
import ru.eltex.accountsystem.model.User;

import java.util.ArrayList;

@SpringBootApplication // автоконфигурирование, обозначение точки входа
public class Registration {
    @Bean // компонент контекста Spring
    public CommandLineRunner demo(UserRepository userRepository) {
        return (args) -> {
            ArrayList<TestResult> testResults = new ArrayList<>();
            testResults.add(new TestResult("math", 22));
            /*userRepository.save(new Student("login", "password",
                    "email", "fio", Role.STUDENT, 23, testResults));*/

            for (User user : userRepository.findAll()) {
                System.out.println(user.getFio());
            }
        };
    }
}

