package ru.eltex.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.eltex.accountsystem.repository.GroupRepository;
import ru.eltex.accountsystem.repository.StudentRepository;
import ru.eltex.accountsystem.model.Subject;
import ru.eltex.accountsystem.model.users.Student;
import ru.eltex.accountsystem.repository.SubjectRepository;
import ru.eltex.testsystem.model.TestStructure;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    private final StudentRepository studentRepository;

//    private final GroupRepository groupRepository;

    private final SubjectRepository subjectRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    @RequestMapping("/get_subjects/{id}")
    public List<Subject> getSubjects(@PathVariable("id") String id) {

        Student student = studentRepository.findById(id).get();
        List<Subject> subjects =  student.getSubjects();
        return subjects;
    }

    @RequestMapping("/get_tests/{id}")
    public List<TestStructure> getTest(@PathVariable("id")  String id) {
        Student student = studentRepository.findById(id).get();
        List<Subject> subjects =  student.getSubjects();
        List<TestStructure> tests = null;
        for(Subject elem: subjects) {
            elem.getTests().forEach(test-> tests.add(test));
        }
        return tests;
    }

    @RequestMapping("/add_subjects")
    public void addSubject(String studentId, String grId) {
        ArrayList<Subject> subjects = new ArrayList<>();
        Student student = studentRepository.findById(studentId).get();
        subjectRepository.findAll().forEach(elem -> elem.getGroups().stream().filter(gr -> gr.getId().equals(grId)).forEach(elem2 -> subjects.add(elem)));
        student.setSubjects(subjects);
        studentRepository.save(student);
    }
}
