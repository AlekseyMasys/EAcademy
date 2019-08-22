package ru.eltex.accountsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.eltex.accountsystem.dao.*;
import ru.eltex.accountsystem.model.Group;
import ru.eltex.accountsystem.model.Subject;
import ru.eltex.accountsystem.model.TaskResult;
import ru.eltex.accountsystem.model.users.Student;
import ru.eltex.accountsystem.model.users.Teacher;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private final TaskResultRepository taskResultRepository;

    @Autowired
    public TeacherService(TeacherRepository _repository, SubjectRepository subjectRepository,
                          TaskResultRepository taskResultRepository) {
        this.teacherRepository = _repository;
        this.subjectRepository = subjectRepository;
        this.taskResultRepository = taskResultRepository;
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

    public void addSubject(Subject subject) {
        subjectRepository.save(subject);
    }

    public void addScores(String studentId,  String taskId, String status, Integer scores) {
        TaskResult task = taskResultRepository.findAll()
                .stream()
                .filter(tsk -> tsk.getIdTask().equals(taskId) && tsk.getIdStudent().equals(studentId))
                .findFirst()
                .orElseThrow();
        task.setScores(scores);
        task.setStatus(status);
        taskResultRepository.save(task);
    }
}