package ru.eltex.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.eltex.accountsystem.dao.StudentRepository;
import ru.eltex.accountsystem.model.Subject;
import ru.eltex.accountsystem.model.users.Student;

import java.util.List;

@RestController
public class StudentController {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @RequestMapping("/get_subjects/{id}")
    public List<Subject> getSubjects(@PathVariable("id") String id) {

        Student student = studentRepository.findById(id).get(0);
    }



}
