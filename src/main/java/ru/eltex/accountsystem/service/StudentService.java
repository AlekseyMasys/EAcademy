package ru.eltex.accountsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.eltex.accountsystem.dao.StudentRepository;
import ru.eltex.accountsystem.dao.SubjectRepository;
import ru.eltex.accountsystem.model.Subject;
import ru.eltex.accountsystem.model.Task;
import ru.eltex.accountsystem.model.users.Student;
import ru.eltex.testsystem.model.TestStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {/*
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    public Student getStudentById(String idStudent) {
        return studentRepository.findById(idStudent).get();
    }

    public ArrayList<Subject> getAllSubjects(String idStudent) {
        return getStudentById(idStudent).getSubjects();
    }

    public Map<Subject, Integer> getMarks(String idStudent) {
        Map<Subject, Integer> marks = new HashMap<>();

        ArrayList<Subject> subjects = getStudentById(idStudent).getSubjects();
        for (Subject subject : subjects) {
            Integer mark = 0;

            for (int i = 0; i < subject.getTasks().size(); i++) {
                mark += subject.getTasks().get(i).getScores();
            }
            for (int i = 0; i < subject.getTests().size(); i++) {
                mark += subject.getTests().get(i).getScore();
            }

            marks.put(subject, mark);
        }

        return marks;
    }

    public Subject getSubjectById(String idSubject) {
        return subjectRepository.findById(idSubject).get();
    }

    public ArrayList<Task> getAllTasksByOneSubject(String idSubject) {
        return new ArrayList<>(getSubjectById(idSubject).getTasks());
    }

    public List<TestStructure> getTests(String idStudent) {
        List<Subject> subjects = studentRepository.findById(idStudent).get().getSubjects();
        List<TestStructure> tests = new ArrayList<>();

        for (Subject subject : subjects) {
            tests.addAll(subject.getTests());
        }

        return tests;
    }*/
}
