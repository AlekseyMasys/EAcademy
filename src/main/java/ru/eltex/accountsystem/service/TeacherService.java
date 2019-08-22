package ru.eltex.accountsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.eltex.accountsystem.dao.SubjectRepository;
import ru.eltex.accountsystem.dao.TaskResultRepository;
import ru.eltex.accountsystem.dao.TeacherRepository;
import ru.eltex.accountsystem.model.TaskResult;
import ru.eltex.accountsystem.model.users.Teacher;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final TaskResultRepository taskResultRepository;

    @Autowired
    public TeacherService(TeacherRepository _repository, SubjectRepository subjectRepository,
                          TaskResultRepository taskResultRepository) {
        this.teacherRepository = _repository;
        this.taskResultRepository = taskResultRepository;
    }

    public Teacher getTeacher(String id) {
        return teacherRepository.findById(id).get();
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