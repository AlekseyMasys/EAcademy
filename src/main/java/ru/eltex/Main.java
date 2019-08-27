package ru.eltex;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.eltex.accountsystem.enums.Role;
import ru.eltex.accountsystem.model.Group;
import ru.eltex.accountsystem.model.Subject;
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

    public static Subject getSubject() {
        ArrayList<String> tasks = new ArrayList<>();
        tasks.add("taskId1");
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("studen\'", "studentPassword", "sss@gmail.com", "Ivanov I.I.", Role.STUDENT, null));
        ArrayList<Group> groups = new ArrayList<>();
        groups.add(new Group("PM-32", "table", students));
        return new Subject("mySubject", tasks, groups, new ArrayList<String>());
    }

    @Bean
    public CommandLineRunner demo(StudentRepository studentRepository, SubjectRepository subjectRepository, TeacherRepository teacherRepository) {
        return (args) -> {
            //добавление предмета
            subjectRepository.save(getSubject());

            ArrayList<String> subjectsId = new ArrayList<>();
            subjectsId.add("5d64fb255156762590876cff");
            Teacher teacher = new Teacher("te4er", "myRassword", "ttt@gmail.com", "Ivanov I.I.", Role.TEACHER, subjectsId);
            teacherRepository.save(teacher);
        };
    }
}
