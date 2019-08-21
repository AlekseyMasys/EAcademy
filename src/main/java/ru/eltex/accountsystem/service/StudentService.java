package ru.eltex.accountsystem.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.eltex.accountsystem.dao.StudentRepository;
import ru.eltex.accountsystem.model.users.Student;


@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public StudentService(StudentRepository studentRepository, ObjectMapper objectMapper) {
        this.studentRepository = studentRepository;
        this.objectMapper = objectMapper;
    }

    public Student getStudentById(String id){
       Student student = studentRepository.findById(id);
       return student;
    }
}
