package ru.eltex.api;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.eltex.accountsystem.model.Group;
import ru.eltex.accountsystem.model.Subject;
import ru.eltex.accountsystem.service.GroupService;
import ru.eltex.accountsystem.service.TeacherService;

@RestController
public class TeacherController {
    private final TeacherService teacherService;
    private final GroupService groupService;

    public TeacherController(TeacherService teacherService, GroupService groupService) {
        this.teacherService = teacherService;
        this.groupService = groupService;
    }

    @RequestMapping(value = "/teacher/{id}/groups", method = RequestMethod.GET)
    public String getTeacherGroups(@PathVariable("id") String id, Model modelGroup) {
        modelGroup.addAllAttributes(teacherService.getTeacherGroups(id));
        return "groups";
    }

    @RequestMapping(value = "/teacher/{id}/subject/{idSubject}", method = RequestMethod.GET)
    public String getSubjectGroups(@PathVariable("id") String id, @PathVariable("idSubject") String idSubject, Model modelGroup) {
        modelGroup.addAllAttributes(teacherService.getSubjectGroups(id, idSubject));
        return "groupsFromSubject";
    }

    @RequestMapping(value = "/getStudentsFromGroup/{id}/{idSubject}/{idGroup}", method = RequestMethod.GET)
    public String getStudentsFromGroup(@PathVariable("id") String id, @PathVariable("idGroup") String idGroup,
                                              @PathVariable String idSubject, Model modelStudents) {
        modelStudents.addAllAttributes(teacherService.getStudentsFromGroup(id, idGroup, idSubject));
        return "studentsFromGroup";
    }

    @RequestMapping(value = "/addGroup", method = RequestMethod.POST)
    public void addGroup(@RequestBody Group group) {
        groupService.addGroup(group);
        //если group.students != null заполнение у студентов subjects
    }

    @RequestMapping(value = "/addStudent/{groupId}/{studentId}", method = RequestMethod.POST)
    public void addStudentInGroup(@PathVariable("groupId") String groupId, @PathVariable("studentId") String studentId) {
        groupService.addStudent(groupId, studentId);
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