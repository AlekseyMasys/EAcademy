package ru.eltex.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.eltex.accountsystem.enums.Role;
import ru.eltex.accountsystem.model.UserRole;
import ru.eltex.accountsystem.service.StudentService;
import ru.eltex.accountsystem.service.TeacherService;
import ru.eltex.accountsystem.service.UserService;

@Controller
public class UserController {
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(TeacherService teacherService, StudentService studentService, UserService userService) {
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.userService = userService;
    }

    // В методах, отдающих страницы, в URL адрессе не должно содержаться слэшей, это меняет работу Thymeleaf.
    // Страницы html НЕ именуются по верблюжьей нотации. Лучше использовать нижнее подчеркивание.

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String get() {
        logger.info("start get()");
        logger.debug("response authorization");
        return "authorization";
    }

    @RequestMapping(value = "user_{id}", method = RequestMethod.POST)
    public String getUser(@PathVariable("id") String id, Model modelUser) {
        logger.info("start getUser()");
        logger.debug("request id = " + id);
        UserRole userRole = userService.getUserRole(id);
        if (userRole.getUserRole().equals(Role.TEACHER)) {
            logger.debug("response teacher_main");
            modelUser.addAttribute("teacher", teacherService.getTeacher(userRole.getUserId()));
            return "teacher_main";
        } else {
            logger.debug("response student_main");
            modelUser.addAttribute("student", studentService.getStudentById(userRole.getUserId()));
            return "student_main";
        }
    }
}