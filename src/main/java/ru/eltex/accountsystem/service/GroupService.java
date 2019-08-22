package ru.eltex.accountsystem.service;

import org.springframework.stereotype.Service;
import ru.eltex.accountsystem.dao.GroupRepository;
import ru.eltex.accountsystem.dao.StudentRepository;
import ru.eltex.accountsystem.model.Group;
import ru.eltex.accountsystem.model.users.Student;

import java.util.ArrayList;

@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;

    public GroupService(GroupRepository groupRepository, StudentRepository studentRepository) {
        this.groupRepository = groupRepository;
        this.studentRepository = studentRepository;
    }

    public void addGroup(Group group) {
        groupRepository.save(group);
        //если group.students != null заполнение у студентов subjects
    }

    private Group getGroup(String id) {
        return groupRepository.findById(id).get();
    }

    public void addStudent(String idGroup, String studentId) {
        Student student = studentRepository.findById(studentId).get();
        Group group = getGroup(idGroup);
        ArrayList<Student> groupStudents = group.getStudents();
        groupStudents.add(student);
        group.setStudents(groupStudents);
        groupRepository.save(group);
        //заполнение у студента subjects
    }
}