package ru.eltex;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.eltex.accountsystem.enums.Role;
import ru.eltex.accountsystem.model.Group;
import ru.eltex.accountsystem.model.Subject;
import ru.eltex.accountsystem.model.Task;
import ru.eltex.accountsystem.model.users.Student;
import ru.eltex.accountsystem.model.users.Teacher;
import ru.eltex.accountsystem.repository.StudentRepository;
import ru.eltex.accountsystem.repository.SubjectRepository;
import ru.eltex.accountsystem.repository.TaskRepository;
import ru.eltex.accountsystem.repository.TeacherRepository;

import java.util.ArrayList;

@SpringBootApplication
public class Main {
    public static void main(java.lang.String[] args) {
        SpringApplication.run(Main.class, args);
    }


    @Bean
    public CommandLineRunner demo(StudentRepository studentRepository, SubjectRepository subjectRepository,
                                  TeacherRepository teacherRepository, TaskRepository taskRepository) {
        return (args) -> {
//            Teacher teacher = teacherRepository.findTeacherById("52b5b0e1-a522-4102-ac9f-932246d15ceb");
//            ArrayList<String> subjectIds = (ArrayList<String>) teacher.getSubjects();
//            subjectIds.add("5d66376b5156767be1c92f77");
//            teacherRepository.save(teacher);

//            ArrayList<String> subjectIds = new ArrayList<>();
//            subjectIds.add("5d6629535156767178379b26");
//
//            Student student = new Student("stuDent", "studentPassword", "sss@gmail.com", "Bob", Role.STUDENT, subjectIds);
//            studentRepository.save(student);
        };
    }
}
