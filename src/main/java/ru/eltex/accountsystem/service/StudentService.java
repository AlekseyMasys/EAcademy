package ru.eltex.accountsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.eltex.accountsystem.dao.TaskResultRepository;
import ru.eltex.accountsystem.model.*;
import ru.eltex.accountsystem.model.users.Student;
import ru.eltex.accountsystem.repository.StudentRepository;
import ru.eltex.accountsystem.repository.SubjectRepository;
import ru.eltex.accountsystem.repository.TestResultRepository;
import ru.eltex.testsystem.repository.TestStructureRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final TaskResultRepository taskResultRepository;
    private final TestResultRepository testResultRepository;
    private final TestStructureRepository testStructureRepository;
    private final TableService tableService;

    @Autowired
    public StudentService(StudentRepository studentRepository, SubjectRepository subjectRepository, TaskResultRepository taskResultRepository, TestResultRepository testResultRepository, TestStructureRepository testStructureRepository, TableService tableService) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.taskResultRepository = taskResultRepository;
        this.testResultRepository = testResultRepository;
        this.testStructureRepository = testStructureRepository;
        this.tableService = tableService;
    }

    public Student getStudentById(String idStudent) {
        return studentRepository.findById(idStudent).get();
    }

    public ArrayList<Subject> getAllSubjects(String idStudent) {
        ArrayList<Subject> subjects = new ArrayList<>();
        getStudentById(idStudent).getSubjects().forEach(elem-> subjects.add(subjectRepository.findById(elem).get()));
        return subjects;
    }

    public Map<Subject, Integer> getMarks(String idStudent) {
        Map<Subject, Integer> marks = new HashMap<>();
        ArrayList<Subject> subjects = new ArrayList<>();
        getStudentById(idStudent).getSubjects().forEach(elem-> subjects.add(subjectRepository.findById(elem).get()));
        for (Subject subject : subjects) {
            Integer mark = 0;
            ArrayList<TaskResult> tasksResult = new ArrayList<>();
            subject.getTasksId().stream().forEach(elem-> tasksResult.add(taskResultRepository.findByIdTask(elem)));

            for(TaskResult elem: tasksResult) {
                mark+= elem.getScores();
            }

            ArrayList<TestResult> testResults = new ArrayList<>();


            subject.getTestsId().forEach(elem-> testResults.add(testResultRepository.findByTestId(elem)));

            for(TestResult elem: testResults) {
                mark+= elem.getResult();
            }

            marks.put(subject, mark);

        }

        return marks;
    }

    public Subject getSubjectById(String idSubject) {
        return subjectRepository.findById(idSubject).get();
    }

    public List<String> getAllTasksByOneSubject(String idSubject) {
        Subject subject = subjectRepository.findById(idSubject).get();
        return subject.getTasksId();
    }

    public List<String> getTests(String idStudent) {
        ArrayList<Subject> subjects = new ArrayList<>();
        getStudentById(idStudent).getSubjects().forEach(elem-> subjects.add(subjectRepository.findById(elem).get()));
        List<String> tests = new ArrayList<>();

        for (Subject subject : subjects) {
            subject.getTestsId().forEach(elem->tests.add(testStructureRepository.findById(elem).get().getName()));

        }

        return tests;
    }

    public void addSubjectForStudent(String studentId, String grId) {
        ArrayList<String> subjects = new ArrayList<>();
        Student student = studentRepository.findById(studentId).get(); //Студент находится в репозитории но его предметы пока еще не заполнены
        //заполнение предметов у студента: получаем все предметы, затем у каждого предмета получаем список групп и если в этом списке
        // присутсвует группа с grId то добавляем id предмета к списку id предметов студента.
        subjectRepository.findAll().forEach(elem -> elem.getGroups().stream().filter(gr -> gr.getId().equals(grId)).forEach(elem2 -> subjects.add(elem.getId()))); //правильность под вопросом=))
        student.setSubjects(subjects);
        studentRepository.save(student);
    }

    public Table getTableForStudent(String studentId) {
        Student student = getStudentById(studentId);
        return tableService.loadTable(student.getGroupId());
    }
}

