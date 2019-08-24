package ru.eltex.api;

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

    @Autowired
    public UserController(TeacherService teacherService, StudentService studentService, UserService userService) {
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.userService = userService;
    }

    @RequestMapping("/")
    public String get() {
        return "authorization";
    }

    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable("id") String id, Model modelUser) {
        UserRole userRole = userService.getUserRole(id);
        if (userRole.getUserRole().equals(Role.TEACHER)) {
            modelUser.addAttribute("teacher", teacherService.getTeacher(userRole.getUserId()));
            return "teacher/main";
        } else {
            modelUser.addAttribute("student", studentService.getStudentById(userRole.getUserId()));
            return "student/main";
        }
    }
}