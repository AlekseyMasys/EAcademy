package ru.eltex;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.eltex.accountsystem.TestResult;
import ru.eltex.accountsystem.dao.UserRepository;

import java.util.ArrayList;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    public CommandLineRunner demo(UserRepository userRepository) {
        return (args) -> {
            TestResult testResult = new TestResult("math", 12);
            ArrayList<TestResult> testResults = new ArrayList<>();
            testResults.add(testResult);
//            userRepository.save(new Student("login", "password",
//                    "email", "fio", Role.STUDENT, 11, testResults));
        };
    }
}
