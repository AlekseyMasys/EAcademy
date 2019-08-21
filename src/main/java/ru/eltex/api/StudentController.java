package ru.eltex.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.eltex.accountsystem.model.Subject;
import ru.eltex.accountsystem.model.Task;
import ru.eltex.accountsystem.model.users.Student;
import ru.eltex.accountsystem.service.StudentService;
import ru.eltex.testsystem.model.TestStructure;

import java.util.List;
import java.util.Map;

@RestController
public class StudentController {

    private final StudentService studentService;


    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping("/get_subjects/{idStudent}")
    public List<Subject> getSubjects(@PathVariable("idStudent") String idStudent) {
        return studentService.getAllSubjects(idStudent);
    }

    @RequestMapping("/get_tasks/{studentId}")
    public List<Task> getAllTasksByOneSubject(@PathVariable("idStudent") String idStudent) {
        return studentService.getAllTasksByOneSubject(idStudent);
    }


    @RequestMapping("/get_tests/{studentId}")
    public List<TestStructure> getTests(@PathVariable("studentId")  String studentId) {
        return studentService.getTests(studentId);
    }

    @RequestMapping("/add_subjects/{studentId}/{grId}")
    public void addSubject(@PathVariable("studentId") String studentId, @PathVariable("grId") String grId) {
        studentService.addSubjectForStudent(studentId, grId);
    }

    @RequestMapping("/get_marks/{studentId}")
    public Map<Subject, Integer> getMarks(@PathVariable("studentId") String studentId) {
        return studentService.getMarks(studentId);
    }

    @RequestMapping("/get_student/{studentId}")
    public Student getStudent(@PathVariable("studentId") String studentId) {
        return studentService.getStudentById(studentId);
    }

    @RequestMapping("/get_subject/{subjectId}")
    public Subject getSubject(@PathVariable("subjectId") String subjectId) {
        return studentService.getSubjectById(subjectId);
    }
}
