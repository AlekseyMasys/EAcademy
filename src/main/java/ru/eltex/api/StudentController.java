package ru.eltex.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.eltex.accountsystem.model.Subject;
import ru.eltex.accountsystem.model.users.Student;
import ru.eltex.accountsystem.repository.GroupRepository;
import ru.eltex.accountsystem.service.StudentService;

import java.util.List;

@Controller
public class StudentController {

    private final StudentService studentService;
    private GroupRepository groupRepository;


    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @RequestMapping("student/{studentId}/get_subjects/")
    public String getSubjects(@PathVariable("studentId") String studentId, Model model) {
        model.addAttribute("subjects", studentService.getAllSubjects(studentId));
        return  "subjectsPage";
    }

    @RequestMapping("student/{studentId}/get_subjects/{subjectId}/get_tasks")
    public String getTasks(@PathVariable("subjectId") String subjectId, Model model) {
        model.addAttribute("subjects", studentService.getAllTasksByOneSubject(subjectId));
        return  "tasksPage";
    }

    @RequestMapping("student/{studentId}/get_tests/")
    public String getTests(@PathVariable("studentId") String studentId, Model model) {
        model.addAttribute("tests", studentService.getTests(studentId));
        return "testsPage";
    }

    @RequestMapping("student/{studentId}/get_schedule")
    public String getSchedule(@PathVariable("studentId") String studentId, Model model) {
        Student student = studentService.getStudentById(studentId);
        String groupId = student.getGroupId();
        return  "schedulePage";
    }



    @RequestMapping("/get_subjects/{idStudent}")
    public List<Subject> getSubjects(@PathVariable("idStudent") String idStudent) {
        return studentService.getAllSubjects(idStudent);
    }

    @RequestMapping("student/{studentId}/get_tasks/")
    public String getAllTasksByOneSubject(@PathVariable("studentId") String studentId, Model model) {
        model.addAttribute("tasks", studentService.getAllTasksByOneSubject(studentId));
        return  "TasksPage";
    }


//    @RequestMapping("/get_tests/{studentId}")
//    public List<TestStructure> getTests(@PathVariable("studentId")  String studentId) {
//        return studentService.getTests(studentId);
//    }

//    @RequestMapping("/add_subjects/{studentId}/{grId}")
//    public void addSubject(@PathVariable("studentId") String studentId, @PathVariable("grId") String grId) {
//        studentService.addSubjectForStudent(studentId, grId);
//    }

//    @RequestMapping("/get_marks/{studentId}")
//    public Map<Subject, Integer> getMarks(@PathVariable("studentId") String studentId) {
//        return studentService.getMarks(studentId);
//    }

//    @RequestMapping("/get_student/{studentId}")
//    public Student getStudent(@PathVariable("studentId") String studentId) {
//        return studentService.getStudentById(studentId);
//    }
//
//    @RequestMapping("/get_subject/{subjectId}")
//    public Subject getSubject(@PathVariable("subjectId") String subjectId) {
//        return studentService.getSubjectById(subjectId);
//    }
}