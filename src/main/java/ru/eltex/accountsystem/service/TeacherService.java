package ru.eltex.accountsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.eltex.accountsystem.dao.GroupRepository;
import ru.eltex.accountsystem.dao.StudentRepository;
import ru.eltex.accountsystem.dao.SubjectRepository;
import ru.eltex.accountsystem.dao.TeacherRepository;
import ru.eltex.accountsystem.model.Group;
import ru.eltex.accountsystem.model.StudentTask;
import ru.eltex.accountsystem.model.Subject;
import ru.eltex.accountsystem.model.users.Student;
import ru.eltex.accountsystem.model.users.Teacher;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public TeacherService(TeacherRepository _repository, GroupRepository groupRepository,
                          StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.teacherRepository = _repository;
        this.groupRepository = groupRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    public Teacher getTeacher(String id) {
        return teacherRepository.findById(id).get();
    }

    public List<Subject> getTeacherSubjects(String id) {
        Teacher teacher = teacherRepository.findById(id).get();
        List<Subject> subjects= new ArrayList<Subject>();
        teacher.getSubjects().stream().forEach(p->{
            subjects.add(subjectRepository.findById(p).get());
        });
        return subjects;
    }

    public List<Group> getTeacherGroups(String id) {
        Teacher teacher = teacherRepository.findById(id).get();
        List<Subject> teacherSubjects= new ArrayList<Subject>();
        teacher.getSubjects().stream().forEach(p->{
            teacherSubjects.add(subjectRepository.findById(p).get());
        });
        List<Group> groups = new ArrayList<>();
        teacherSubjects.forEach(sub -> groups.addAll(sub.getGroups()));
        return groups;
    }

    public List<Group> getSubjectGroups(String id, String idSubject) {
        Teacher teacher = teacherRepository.findById(id).get();
        List<Subject> teacherSubjects= new ArrayList<Subject>();
        teacher.getSubjects().stream().forEach(p->{
            teacherSubjects.add(subjectRepository.findById(p).get());
        });
        return teacherSubjects.stream().filter(sub -> sub.getId().equals(idSubject)).findFirst().orElseThrow().getGroups();
    }

    public List<Student> getStudentsFromGroup(String id, String idGroup, String idSubject) {
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

    public void addGroup(Group group) {
        groupRepository.save(group);
        //если group.students != null заполнение у студентов subjects
    }

    public void addStudentInGroup(String groupId, String studentId) {
        Group group = groupRepository.findById(groupId).get();
        Student student = studentRepository.findById(studentId).get();
        ArrayList<Student> groupStudents = group.getStudents();
        groupStudents.add(student);
        group.setStudents(groupStudents);
        groupRepository.save(group);
        //заполнение у студента subjects
    }

    public void addSubject(Subject subject) {
        subjectRepository.save(subject);
    }

    public void addScores(String studentId, String subjectId, String taskId, Integer scores) {
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