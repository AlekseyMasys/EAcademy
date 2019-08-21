package ru.eltex;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.eltex.accountsystem.dao.StudentRepository;
import ru.eltex.accountsystem.dao.TeacherRepository;
import ru.eltex.accountsystem.enums.Role;
import ru.eltex.accountsystem.model.Group;
import ru.eltex.accountsystem.model.Subject;
import ru.eltex.accountsystem.model.Task;
import ru.eltex.accountsystem.model.users.Student;
import ru.eltex.accountsystem.model.users.Teacher;
import ru.eltex.testsystem.model.TestStructure;

import java.util.ArrayList;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner demo(StudentRepository studentRepository, TeacherRepository teacherRepository) {
        return (args) -> {


            ArrayList<Subject> subjects = new ArrayList<>();
            subjects.add(new Subject("title", new ArrayList<Task>(), new ArrayList<Group>(), new ArrayList<TestStructure>()));
            subjects.add(new Subject("math", new ArrayList<Task>(), new ArrayList<Group>(), new ArrayList<TestStructure>()));


            Student student = new Student("student", "password", "qwerty@mail.ru", "Alex", Role.STUDENT, subjects);
            studentRepository.save(student);

            Teacher teacher = new Teacher("teacher", "password", "qwerty@mail.ru", "Bob", Role.TEACHER, subjects);
            teacherRepository.save(teacher);
        };
    }
}
