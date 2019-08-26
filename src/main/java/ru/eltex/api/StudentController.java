package ru.eltex.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.eltex.accountsystem.model.Subject;
import ru.eltex.accountsystem.service.StudentService;

import java.util.List;

@Controller
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "student/{studentId}/get_subjects/", method = RequestMethod.GET)
    public String getSubjects(@PathVariable("studentId") String studentId, Model model) {
        model.addAttribute("subjects", studentService.getAllSubjects(studentId));
        return "student/subjects";
    }

    @RequestMapping(value = "student/{studentId}/get_subjects/{subjectId}/get_tasks", method = RequestMethod.GET)
    public String getTasks(@PathVariable("studentId") String studentId, @PathVariable("subjectId") String subjectId, Model model) {
        model.addAttribute("subjects", studentService.getAllTasksByOneSubject(subjectId));
        return "students/tasks";
    }

    @RequestMapping(value = "student/{studentId}/get_tests/", method = RequestMethod.GET)
    public String getTests(@PathVariable("studentId") String studentId, Model model) {
        model.addAttribute("tests", studentService.getTests(studentId));
        return "student/tests";
    }

    @RequestMapping(value = "get_subjects/{idStudent}", method = RequestMethod.GET)
    public List<Subject> getSubjects(@PathVariable("idStudent") String idStudent) {
        return studentService.getAllSubjects(idStudent);
    }

    @RequestMapping(value = "student/{studentId}/get_tasks/", method = RequestMethod.GET)
    public String getAllTasksByOneSubject(@PathVariable("studentId") String studentId, Model model) {
        model.addAttribute("tasks", studentService.getAllTasksByOneSubject(studentId));
        return "student/tasks";
    }

    @RequestMapping(value = "student/{studentId}/get_table/", method = RequestMethod.GET)
    public String getTable(@PathVariable("studentId") String studentId, Model model) {
        model.addAttribute("table", studentService.getTableForStudent(studentId));
        return "table";
    }
}