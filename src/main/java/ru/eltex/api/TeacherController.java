package ru.eltex.api;

import org.springframework.web.bind.annotation.*;
import ru.eltex.accountsystem.model.Group;
import ru.eltex.accountsystem.model.Subject;
import ru.eltex.accountsystem.model.users.Student;
import ru.eltex.accountsystem.model.users.Teacher;
import ru.eltex.accountsystem.service.TeacherService;

import java.util.List;

@RestController
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @RequestMapping(value = "/teacher/{id}", method = RequestMethod.GET)
    public Teacher getTeacher(@PathVariable("id") String id) {
        return teacherService.getTeacher(id);
    }

    @RequestMapping(value = "/teacher/{id}/subjects", method = RequestMethod.GET)
    public List<Subject> getTeacherSubjects(@PathVariable("id") String id) {
       return teacherService.getTeacherSubjects(id);
    }

    @RequestMapping(value = "/teacher/{id}/groups", method = RequestMethod.GET)
    public List<Group> getTeacherGroups(@PathVariable("id") String id) {
        return teacherService.getTeacherGroups(id);
    }

    @RequestMapping(value = "/teacher/{id}/subject/{idSubject}", method = RequestMethod.GET)
    public List<Group> getSubjectGroups(@PathVariable("id") String id, @PathVariable("idSubject") String idSubject) {
        return teacherService.getSubjectGroups(id, idSubject);
    }

    @RequestMapping(value = "/getStudentsFromGroup/{id}/{idSubject}/{idGroup}", method = RequestMethod.GET)
    public List<Student> getStudentsFromGroup(@PathVariable("id") String id, @PathVariable("idGroup") String idGroup, @PathVariable String idSubject) {
        return teacherService.getStudentsFromGroup(id, idGroup, idSubject);
    }

    @RequestMapping(value = "/addGroup", method = RequestMethod.POST)
    public void addGroup(@RequestBody Group group) {
        teacherService.addGroup(group);
        //если group.students != null заполнение у студентов subjects
    }

    @RequestMapping(value = "/addStudent/{groupId}/{studentId}", method = RequestMethod.POST)
    public void addStudentInGroup(@PathVariable("groupId") String groupId, @PathVariable("studentId") String studentId) {
        teacherService.addStudentInGroup(groupId, studentId);
        //заполнение у студента subjects
    }

    @RequestMapping(value = "/addSubject", method = RequestMethod.POST)
    public void addSubject(@RequestBody Subject subject) {
        teacherService.addSubject(subject);
    }

    @RequestMapping(value = "/addScores/{studentId}/{taskId}/{scores}/{status}", method = RequestMethod.POST)
    public void addScores(@PathVariable("studentId") String studentId,
                          @PathVariable("taskId") String taskId,
                          @PathVariable("scores") Integer scores,
                          @PathVariable("status") String status) {
        teacherService.addScores(studentId, taskId, status, scores);
    }
}