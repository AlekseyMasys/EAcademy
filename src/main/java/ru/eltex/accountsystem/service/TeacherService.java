package ru.eltex.accountsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.eltex.accountsystem.dao.*;
import ru.eltex.accountsystem.enums.TaskStatus;
import ru.eltex.accountsystem.model.Group;
import ru.eltex.accountsystem.model.Subject;
import ru.eltex.accountsystem.model.Task;
import ru.eltex.accountsystem.model.TaskResult;
import ru.eltex.accountsystem.model.users.Student;
import ru.eltex.accountsystem.model.users.Teacher;
import ru.eltex.accountsystem.repository.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final GroupRepository groupRepository;
    private final TaskResultRepository taskResultRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public TeacherService(TeacherRepository _repository, StudentRepository studentRepository, SubjectRepository subjectRepository,
                          GroupRepository groupRepository, TaskResultRepository taskResultRepository, TaskRepository taskRepository) {
        this.teacherRepository = _repository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.groupRepository = groupRepository;
        this.taskResultRepository = taskResultRepository;
        this.taskRepository = taskRepository;
    }

    public Teacher getTeacher(String id) {
        return teacherRepository.findById(id).get();
    }

    public List<String> getTeacherSubjects(String id) {
        Teacher teacher = teacherRepository.findById(id).get();
        return teacher.getSubjectIds();
    }

    public List<Group> getTeacherGroups(String id) {
        Teacher teacher = teacherRepository.findById(id).get();
        List<Subject> teacherSubjects = new ArrayList<>();
        teacher.getSubjectIds().stream().forEach(p -> {
            teacherSubjects.add(subjectRepository.findById(p).get());
        });
        List<Group> groups = new ArrayList<>();
        List<String> groupIds = new ArrayList<>();
        teacherSubjects.forEach(sub -> groupIds.addAll(sub.getGroupIds()));

        groupIds.forEach(group -> groups.add(groupRepository.findById(group).get()));
        return groups;
    }

    public List<Group> getSubjectGroups(String idSubject) {
        Subject subject = subjectRepository.findById(idSubject).get();
        List<Group> groups = new ArrayList<>();
        subject.getGroupIds().forEach(groupId -> groups.add(groupRepository.findById(groupId).get()));
        return groups;
    }

    public List<Student> getStudentsFromGroup(String idGroup) {
        Group group = groupRepository.findById(idGroup).get();
        List<Student> students = new ArrayList<>();
        group.getStudentIds().forEach(studentId -> students.add(studentRepository.findById(studentId).get()));
        return students;
    }

    public void addSubject(Subject subject) {
        subjectRepository.save(subject);
    }

    public void addScores(String studentId, String taskId, String status, Integer scores) {
        TaskResult task = taskResultRepository.findAll()
                .stream()
                .filter(tsk -> tsk.getIdTask().equals(taskId) && tsk.getIdStudent().equals(studentId))
                .findFirst()
                .orElseThrow(RuntimeException::new);
        task.setScores(scores);
        task.setTaskStatus(TaskStatus.valueOf(status));
        taskResultRepository.save(task);
    }

    public void addTask(String teacherId, String subjectId, Task task) {
        Subject subject = subjectRepository.findById(subjectId).get();
        List<String> tasksIdList = subject.getTaskIds();
        taskRepository.save(task);
        tasksIdList.add(task.getId());
        subject.setTaskIds(tasksIdList);
        subjectRepository.save(subject);
        List<String> listGroupsId = subject.getGroupIds();

        for(String idGroup: listGroupsId) {  //добавление тасок всем студентам которые есть в группах пренадлежащих переданному в метод предмету
            Group group = groupRepository.findById(idGroup).get();
            List<String> studentsId = group.getStudentIds();
            for(String idStudent: studentsId) {
                TaskResult taskResult = new TaskResult(teacherId, idStudent, null, null);
                taskResultRepository.save(taskResult);
            }
        }
    }

    public List<TaskResult> getTasks(String groupId) {
        List<TaskResult> taskResults = new ArrayList<>();
        Group group = groupRepository.findById(groupId).get();
        List<String> studentsId = group.getStudentIds();
        for(String idStudent: studentsId) {
            taskResults.add(taskResultRepository.findByIdStudent(idStudent));
        }
        return  taskResults;
    }
}