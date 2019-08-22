package ru.eltex.api;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.eltex.accountsystem.model.Group;
import ru.eltex.accountsystem.model.Subject;
import ru.eltex.accountsystem.model.users.Teacher;
import ru.eltex.accountsystem.service.GroupService;
import ru.eltex.accountsystem.service.SubjectService;
import ru.eltex.accountsystem.service.TeacherService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TeacherController {
    private final TeacherService teacherService;
    private final GroupService groupService;
    private final SubjectService subjectService;

    public TeacherController(TeacherService teacherService, GroupService groupService, SubjectService subjectService) {
        this.teacherService = teacherService;
        this.groupService = groupService;
        this.subjectService = subjectService;
    }

    @RequestMapping(value = "/teacher/{id}", method = RequestMethod.GET)
    public String getTeacher(@PathVariable("id") String id, Model modelTeacher) {
        modelTeacher.addAttribute("teacher", teacherService.getTeacher(id));
        return "teacher";
    }

    @RequestMapping(value = "/teacher/{id}/subjects", method = RequestMethod.GET)
    public String getTeacherSubjects(@PathVariable("id") String id, Model modelSubjects) {
        modelSubjects.addAllAttributes(getSubjects(id));
        return "teacherSubjects";
    }

    private List<Subject> getSubjects(String id) {
        List<Subject> subjects = new ArrayList<>();
        Teacher teacher = teacherService.getTeacher(id);
        teacher.getSubjects()
                .stream()
                .filter(sub -> subjects.add(subjectService.getSubject(sub)))
                .collect(Collectors.toList());
        return subjects;
    }

    @RequestMapping(value = "/teacher/{id}/groups", method = RequestMethod.GET)
    public String getTeacherGroups(@PathVariable("id") String id, Model modelGroup) {
        List<Group> groups = getGroups(id);
        modelGroup.addAllAttributes(groups.stream().distinct().collect(Collectors.toList()));
        return "groups";
    }

    private List<Group> getGroups(String id) {
        List<Subject> subjects = getSubjects(id);
        List<Group> groups = new ArrayList<>();
        subjects.forEach(sub -> groups.addAll(sub.getGroups()));
        return groups;
    }

    @RequestMapping(value = "/teacher/{id}/subject/{idSubject}", method = RequestMethod.GET)
    public String getSubjectGroups(@PathVariable("id") String id, @PathVariable("idSubject") String idSubject, Model modelGroup) {
        Subject subject = subjectService.getSubject(idSubject);
        modelGroup.addAllAttributes(subject.getGroups());
        return "groupsFromSubject";
    }

    @RequestMapping(value = "/getStudentsFromGroup/{id}/{idSubject}/{idGroup}", method = RequestMethod.GET)
    public String getStudentsFromGroup(@PathVariable("id") String id, @PathVariable("idGroup") String idGroup,
                                              @PathVariable String idSubject, Model modelStudents) {
        List<Group> groups = getGroups(id);
        modelStudents.addAllAttributes(groups.stream().filter(group -> group.getId().equals(idGroup)).findFirst().get().getStudents());
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
        subjectService.addSubject(subject);
    }

    @RequestMapping(value = "/addScores/{studentId}/{taskId}/{scores}/{status}", method = RequestMethod.POST)
    public void addScores(@PathVariable("studentId") String studentId,
                          @PathVariable("taskId") String taskId,
                          @PathVariable("scores") Integer scores,
                          @PathVariable("status") String status) {
        teacherService.addScores(studentId, taskId, status, scores);
    }
}