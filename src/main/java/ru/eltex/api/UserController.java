package ru.eltex.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.eltex.accountsystem.enums.Role;
import ru.eltex.accountsystem.model.UserRole;
import ru.eltex.accountsystem.service.StudentService;
import ru.eltex.accountsystem.service.TeacherService;

@RestController
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

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String getTeacher(@PathVariable("id") String id, Model modelUser) {
        UserRole userRole = userServicel.getUserRole(id);
        if (userRole.getUserRole().equals(Role.TEACHER)) {
            modelUser.addAttribute("teacher", teacherService.getTeacher(userRole.getUserId()));
            return "teacher";
        }
        else {
            modelUser.addAttribute("student", studentService.getStudentById(userRole.getUserId()));
            return "student";
        }
    }

    @RequestMapping(value = "/user/{id}/subjects", method = RequestMethod.GET)
    public String getUserSubjects(@PathVariable("id") String id, Model modelSubjects) {
        UserRole userRole = userServicel.getUserRole(id);
        if (userRole.getUserRole().equals(Role.TEACHER)) {
            modelSubjects.addAllAttributes(teacherService.getTeacherSubjects(userRole.getUserId()));
            return "teacherSubjects";
        }
        else {
            modelSubjects.addAllAttributes(studentService.getAllSubjects(userRole.getUserId()));
            return "studentSubjects";
        }
    }
}