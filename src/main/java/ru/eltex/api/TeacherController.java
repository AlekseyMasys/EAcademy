package ru.eltex.api;

import org.springframework.web.bind.annotation.*;
import ru.eltex.accountsystem.dao.GroupRepository;
import ru.eltex.accountsystem.dao.StudentRepository;
import ru.eltex.accountsystem.dao.SubjectRepository;
import ru.eltex.accountsystem.dao.TeacherRepository;
import ru.eltex.accountsystem.model.Group;
import ru.eltex.accountsystem.model.StudentTask;
import ru.eltex.accountsystem.model.Subject;
import ru.eltex.accountsystem.model.users.Student;
import ru.eltex.accountsystem.model.users.Teacher;

import java.util.*;

@RestController
public class TeacherController {
    private final TeacherRepository teacherRepository;
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    public TeacherController(TeacherRepository _repository, GroupRepository groupRepository,
                             StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.teacherRepository = _repository;
        this.groupRepository = groupRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    @RequestMapping(value = "/teacher/{id}", method = RequestMethod.GET)
    public Teacher getTeacher(@PathVariable("id") String id) {
        return teacherRepository.findById(id).get();
    }

    @RequestMapping(value = "/teacher/{id}/subjects", method = RequestMethod.GET)
    public List<Subject> getTeacherSubjects(@PathVariable("id") String id) {
        Teacher teacher = teacherRepository.findById(id).get();
        List<Subject> subjects= new ArrayList<Subject>();
        teacher.getSubjects().stream().forEach(p->{
            subjects.add(subjectRepository.findById(p).get());
        });
        return subjects;
    }

    @RequestMapping(value = "/teacher/{id}/groups", method = RequestMethod.GET)
    public List<Group> getTeacherGroups(@PathVariable("id") String id) {
        Teacher teacher = teacherRepository.findById(id).get();
        List<Subject> teacherSubjects= new ArrayList<Subject>();
        teacher.getSubjects().stream().forEach(p->{
            teacherSubjects.add(subjectRepository.findById(p).get());
        });
        List<Group> groups = new ArrayList<>();
        teacherSubjects.forEach(sub -> groups.addAll(sub.getGroups()));
        return groups;
    }

    @RequestMapping(value = "/teacher/{id}/subject/{idSubject}", method = RequestMethod.GET)
    public List<Group> getSubjectGroups(@PathVariable("id") String id, @PathVariable("idSubject") String idSubject) {
        Teacher teacher = teacherRepository.findById(id).get();
        List<Subject> teacherSubjects= new ArrayList<Subject>();
        teacher.getSubjects().stream().forEach(p->{
            teacherSubjects.add(subjectRepository.findById(p).get());
        });
        return teacherSubjects.stream().filter(sub -> sub.getId().equals(idSubject)).findFirst().orElseThrow().getGroups();
    }

    @RequestMapping(value = "/getstudentsfromgroup/{id}/{idSubjec}/{idGroup}", method = RequestMethod.GET)
    public List<Student> getStudentsFromGroup(@PathVariable("id") String id, @PathVariable("idGroup") String idGroup, @PathVariable String idSubject) {
        Teacher teacher = teacherRepository.findById(id).get();
        List<Subject> teacherSubjects= new ArrayList<Subject>();
        teacher.getSubjects().stream().forEach(p->{
            teacherSubjects.add(subjectRepository.findById(p).get());
        });
        List<Group> groups =  teacherSubjects.stream().filter(sub -> sub
                .getId()
                .equals(idSubject))
                .findFirst()
                .orElseThrow()
                .getGroups();
        Group group = groups.stream().filter(gr -> gr.getId().equals(idGroup)).findFirst().orElseThrow();
        return group.getStudents();
    }

    @RequestMapping(value = "/addgroup", method = RequestMethod.POST)
    @ResponseBody
    public void addGroup(@RequestBody Group group) {
        groupRepository.save(group);
        //если group.students != null заполнение у студентов subjects
    }

    @RequestMapping(value = "/addstudent/{groupId}/{studentId}", method = RequestMethod.POST)
    public void addStudentInGroup(@PathVariable("groupId") String groupId, @PathVariable("studentId") String studentId) {
        Group group = groupRepository.findById(groupId).get();
        Student student = studentRepository.findById(studentId).get();
        ArrayList<Student> groupStudents = group.getStudents();
        groupStudents.add(student);
        group.setStudents(groupStudents);
        groupRepository.save(group);
        //заполнение у студента subjects
    }

    @RequestMapping(value = "/addsubject", method = RequestMethod.POST)
    public void addSubject(@RequestBody Subject subject) {
        subjectRepository.save(subject);
    }

    @RequestMapping(value = "/addscores/{studentId}/{subjectId}/{taskId}/{scores}", method = RequestMethod.POST)
    public void addScores(@PathVariable("studentId") String studentId,
                          @PathVariable("subjectId") String subjectId,
                          @PathVariable("taskId") String taskId,
                          @PathVariable("scores") Integer scores) {
        Student student = studentRepository.findById(studentId).get();
        Subject studenSubject = student.getSubjects().stream().filter(sub -> sub.getId().equals(subjectId)).findFirst().orElseThrow();
        StudentTask studentTask = (StudentTask) studenSubject
                .getTasks()
                .stream()
                .filter(task -> taskId.equals(((StudentTask)task).getIdTeacherTask()))
                .findFirst()
                .orElseThrow();
        studentTask.setScores(scores);
        studentRepository.save(student);
    }
}