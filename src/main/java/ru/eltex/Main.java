package ru.eltex;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.eltex.accountsystem.enums.Role;
import ru.eltex.accountsystem.model.Group;
import ru.eltex.accountsystem.model.Subject;
import ru.eltex.accountsystem.model.User;
import ru.eltex.accountsystem.model.users.Student;
import ru.eltex.accountsystem.model.users.Teacher;
import ru.eltex.accountsystem.repository.StudentRepository;
import ru.eltex.accountsystem.repository.SubjectRepository;
import ru.eltex.accountsystem.repository.TeacherRepository;

import java.util.ArrayList;


@SpringBootApplication
public class Main {
    public static void main(java.lang.String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Autowired
    ObjectMapper objectMapper;

    @Bean
    public CommandLineRunner demo(StudentRepository studentRepository, SubjectRepository subjectRepository, TeacherRepository teacherRepository) {
        return (args) -> {
//            User user =
            Student student = new Student("login", "password", "email", "fio", Role.STUDENT, null);
            System.out.println(objectMapper.writeValueAsString(student));

//            List<String> subjects = new ArrayList<>();
//            Subject subject = new Subject("title", new ArrayList<String>(), new ArrayList<Group>(), new ArrayList<String>());
//            subjectRepository.save(subject);
//
//            Teacher teacher = new Teacher("teacher", "password", "qwerty@mail.ru", "Bob", Role.TEACHER, subjects);
//            teacherRepository.save(teacher);

        };
    }
}
