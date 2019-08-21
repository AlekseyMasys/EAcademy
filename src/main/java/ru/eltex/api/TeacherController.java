package ru.eltex.api;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.*;
import ru.eltex.accountsystem.dao.TeacherRepository;
import ru.eltex.accountsystem.model.Group;
import ru.eltex.accountsystem.model.Subject;
import ru.eltex.accountsystem.model.users.Student;
import ru.eltex.accountsystem.model.users.Teacher;

import java.util.*;

@RestController
public class TeacherController {
    private final TeacherRepository teacherRepository;

    public TeacherController(TeacherRepository _repository) {
        this.teacherRepository = _repository;
    }

    @RequestMapping(value = "/teacher/{id}", method = RequestMethod.GET)
    public Teacher getTeacher(@PathVariable("id") String id) {
        return teacherRepository.findById(id).get();
    }

    @RequestMapping(value = "/teacher/{id}/subjects", method = RequestMethod.GET)
    public List<Subject> getTeacherSubjects(@PathVariable("id") String id) {
        Teacher teacher = teacherRepository.findById(id).get();
        return teacher.getSubjects();
    }

    @RequestMapping(value = "/teacher/{id}/groups", method = RequestMethod.GET)
    public List<Group> getTeacherGroups(@PathVariable("id") String id) {
        Teacher teacher = teacherRepository.findById(id).get();
        List<Subject> teacherSubjects = teacher.getSubjects();
        List<Group> groups = new ArrayList<>();
        teacherSubjects.forEach(sub -> groups.addAll(sub.getGroups()));
        return groups;
    }

    @RequestMapping(value = "/teacher/{id}/subject/{idSubject}", method = RequestMethod.GET)
    public List<Group> getSubjectGroups(@PathVariable("id") String id, @PathVariable("idSubject") String idSubject) {
        Teacher teacher = teacherRepository.findById(id).get();
        List<Subject> teacherSubjects = teacher.getSubjects();
        return teacherSubjects.stream().filter(sub -> sub.getId().equals(idSubject)).findFirst().orElseThrow().getGroups();
    }

    @RequestMapping(value = "/getstudentsfromgroup/{id}/{idSubjec}/{idGroup}", method = RequestMethod.GET)
    public List<Student> getStudentsFromGroup(@PathVariable("id") String id, @PathVariable("idGroup") String idGroup, @PathVariable String idSubject) {
        Teacher teacher = teacherRepository.findById(id).get();
        List<Subject> teacherSubjects = teacher.getSubjects();
        List<Group> groups =  teacherSubjects.stream().filter(sub -> sub.getId().equals(idSubject)).findFirst().orElseThrow().getGroups();
        Group group = groups.stream().filter(gr -> gr.getId().equals(idGroup)).findFirst().orElseThrow();
        return group.getStudents();
    }
}
