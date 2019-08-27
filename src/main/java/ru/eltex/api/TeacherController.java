package ru.eltex.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.eltex.accountsystem.model.Group;
import ru.eltex.accountsystem.model.Subject;
import ru.eltex.accountsystem.model.users.Teacher;
import ru.eltex.accountsystem.service.GroupService;
import ru.eltex.accountsystem.service.StudentService;
import ru.eltex.accountsystem.service.TeacherService;

@Controller
public class TeacherController {
    private final TeacherService teacherService;
    private final GroupService groupService;
    private final StudentService studentService;

    @Autowired
    public TeacherController(TeacherService teacherService, GroupService groupService, StudentService studentService) {
        this.teacherService = teacherService;
        this.groupService = groupService;
        this.studentService = studentService;
    }

    // В методах, отдающих страницы, в URL адрессе не должно содержаться слэшей, это меняет работу Thymeleaf.
    // Страницы html НЕ менуются по верблюжьей нотации. Лучше использовать нижнее подчеркивание.

    @RequestMapping(value = "/teacher_{id}", method = RequestMethod.GET)
    public String getTeacher(@PathVariable("id") String id, Model modelTeacher) {
        modelTeacher.addAttribute("teacher", teacherService.getTeacher(id));
        return "teacher_main";
    }

    @RequestMapping(value = "/teacher_{id}_subjects", method = RequestMethod.GET)
    public String getTeacherSubjects(@PathVariable("id") String id, Model modelSubjects) {
        modelSubjects.addAllAttributes(teacherService.getTeacherSubjects(id));
        return "teacher_subjects";
    }

    @RequestMapping(value = "/teacher_{id}_groups", method = RequestMethod.GET)
    public String getTeacherGroups(@PathVariable("id") String id, Model modelGroup) {
        modelGroup.addAllAttributes(teacherService.getTeacherGroups(id));
        return "teacher_groups";
    }

    @RequestMapping(value = "/teacher_{id}_subject_{idSubject}", method = RequestMethod.GET)
    public String getSubjectGroups(@PathVariable("id") String id, @PathVariable("idSubject") String idSubject, Model modelGroup) {
        modelGroup.addAllAttributes(teacherService.getSubjectGroups(id, idSubject));
        return "teacher_subjectGroups";
    }

    @RequestMapping(value = "/teacher_{id}_getStudentsFromGroup_{idSubject}_{idGroup}", method = RequestMethod.GET)
    public String getStudentsFromGroup(@PathVariable("id") String id, @PathVariable("idGroup") String idGroup,
                                       @PathVariable String idSubject, Model modelStudents) {
        modelStudents.addAllAttributes(teacherService.getStudentsFromGroup(id, idGroup, idSubject));
        return "teacher_students_from_group";
    }

    //REST METHODS
    @GetMapping(value = "/teacher/{id}/getInfo")
    @ResponseBody
    public Teacher getTeacherInfo(@PathVariable("id") String id) {
        return teacherService.getTeacher(id);
    }

    @RequestMapping(value = "/addGroup", method = RequestMethod.POST)
    @ResponseBody
    public void addGroup(@RequestBody Group group) {
        groupService.addGroup(group);
        //если group.students != null заполнение у студентов subjects
    }

    @RequestMapping(value = "/addStudent/{groupId}/{studentId}", method = RequestMethod.POST)
    @ResponseBody
    public void addStudentInGroup(@PathVariable("groupId") String groupId, @PathVariable("studentId") String studentId) {
        groupService.addStudent(groupId, studentId);
        studentService.addSubjectForStudent(studentId, groupId);
        //заполнение у студента subjects
    }

    @RequestMapping(value = "/addSubject", method = RequestMethod.POST)
    @ResponseBody
    public void addSubject(@RequestBody Subject subject) {
        teacherService.addSubject(subject);
    }

    @RequestMapping(value = "/addScores/{studentId}/{taskId}/{scores}/{status}", method = RequestMethod.POST)
    @ResponseBody
    public void addScores(@PathVariable("studentId") String studentId,
                          @PathVariable("taskId") String taskId,
                          @PathVariable("scores") Integer scores,
                          @PathVariable("status") String status) {
        teacherService.addScores(studentId, taskId, status, scores);
    }
}