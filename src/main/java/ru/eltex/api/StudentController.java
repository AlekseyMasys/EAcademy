package ru.eltex.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.eltex.accountsystem.model.Subject;
import ru.eltex.accountsystem.service.StudentService;

import java.util.List;

@Controller
public class StudentController {
    private final StudentService studentService;
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // В методах, отдающих страницы, в URL адрессе не должно содержаться слэшей, это меняет работу Thymeleaf.
    // Страницы html НЕ менуются по верблюжьей нотации. Лучше использовать нижнее подчеркивание.

    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    public String getStudent(@PathVariable("id") String id, Model modelTeacher) {
        logger.info("start getStudent()");
        logger.debug("request id = " + id);
        logger.debug("response student_main");
        modelTeacher.addAttribute("student", studentService.getStudentById(id));
        return "student_main";
    }

    @RequestMapping(value = "student/{studentId}/subjects", method = RequestMethod.GET)
    public String getSubjects(@PathVariable("studentId") String studentId, Model model) {
        logger.info("start getSubjects()");
        logger.debug("request studentId = " + studentId);
        logger.debug("response student_subjects");
        model.addAttribute("subjects", studentService.getAllSubjects(studentId));
        return "student_subjects";
    }

    @RequestMapping(value = "student/{studentId}/subjects/{subjectId}/tasks", method = RequestMethod.GET)
    public String getTasks(@PathVariable("studentId") String studentId, @PathVariable("subjectId") String subjectId, Model model) {
        logger.info("start getTasks()");
        logger.debug("request studentId = " + studentId + "subjectId = " + studentId);
        logger.debug("response student_tasks");
        model.addAttribute("subjects", studentService.getAllTasksByOneSubject(subjectId));
        return "student_tasks";
    }

    @RequestMapping(value = "student/{studentId}/tests", method = RequestMethod.GET)
    public String getTests(@PathVariable("studentId") String studentId, Model model) {
        logger.info("start getTests()");
        logger.debug("request studentId = " + studentId);
        logger.debug("response student_tests");
        model.addAttribute("tests", studentService.getTests(studentId));
        return "student_tests";
    }

    @RequestMapping(value = "student/{studentId}/table", method = RequestMethod.GET)
    public String getTable(@PathVariable("studentId") String studentId, Model model) {
        logger.info("start getTable()");
        logger.debug("request studentId = " + studentId);
        logger.debug("response student_timetable");
        model.addAttribute("table", studentService.getTableForStudent(studentId));
        return "student_timetable";
    }

//        @RequestMapping(value = "student_{studentId}_getTasks", method = RequestMethod.GET)
//    public String getAllTasksByOneSubject(@PathVariable("studentId") String studentId, Model model) {
//        model.addAttribute("tasks", studentService.getAllTasksByOneSubject(studentId));
//        return "student_tasks";
//    }

    //REST METHOD
    @RequestMapping(value = "student/{studentId}/subjects", method = RequestMethod.POST)
    @ResponseBody
    public List<Subject> getSubjects(@PathVariable("studentId") String idStudent) {
        logger.info("start getSubjects()");
        logger.debug("request studentId = " + idStudent);
        List<Subject> subjects = studentService.getAllSubjects(idStudent);
        StringBuilder subjectsToString = new StringBuilder();
        for (Subject subject: subjects) {
            subjectsToString.append(subject);
            subjectsToString.append(" ");
        }
        logger.debug("response " + subjectsToString.toString());
        return subjects;
    }
}