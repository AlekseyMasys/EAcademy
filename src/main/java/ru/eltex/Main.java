package ru.eltex;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.eltex.accountsystem.dao.StudentRepository;
import ru.eltex.accountsystem.dao.TeacherRepository;
import ru.eltex.accountsystem.dao.SubjectRepository;
import ru.eltex.accountsystem.enums.Role;
import ru.eltex.accountsystem.model.Group;
import ru.eltex.accountsystem.model.Subject;
import ru.eltex.accountsystem.model.Task;
import ru.eltex.accountsystem.model.users.Teacher;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Main {
    public static void main(java.lang.String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner demo(StudentRepository studentRepository, SubjectRepository subjectRepository, TeacherRepository teacherRepository) {
        return (args) -> {

            ArrayList<String> subjects = new ArrayList<>();
            Subject subject = new Subject("title", new ArrayList<Task>(), new ArrayList<Group>(), new ArrayList<String>());
            subjectRepository.save(subject);

            Teacher teacher = new Teacher("teacher", "password", "qwerty@mail.ru", "Bob", Role.TEACHER, subjects);
            teacherRepository.save(teacher);
        };
    }
}
