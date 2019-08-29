package ru.eltex.testsystem.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.eltex.accountsystem.model.Subject;
import ru.eltex.accountsystem.model.TestResult;
import ru.eltex.accountsystem.repository.SubjectRepository;
import ru.eltex.accountsystem.repository.TeacherRepository;
import ru.eltex.accountsystem.repository.TestResultRepository;
import ru.eltex.testsystem.model.QuestionModel;
import ru.eltex.testsystem.model.TestAnswers;
import ru.eltex.testsystem.model.TestStructure;
import ru.eltex.testsystem.repository.TestStructureRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestStructureService {
    private final TestStructureRepository testStructureRepository;
    private final ObjectMapper objectMapper;
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;


    @Autowired
    public TestStructureService(TestStructureRepository testStructureRepository, ObjectMapper objectMapper,
                                TeacherRepository teacherRepository, SubjectRepository subjectRepository, TestResultRepository testResultRepository) {
        this.testStructureRepository = testStructureRepository;
        this.objectMapper = objectMapper;
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
    }

    public void saveTest(TestStructure request, String id, String idSubject) {
        Subject subject = subjectRepository.findById(idSubject).get();
        List<String> testNames = subject.getTestIds();
        testNames.add(request.getId());
        subjectRepository.save(subject);
        testStructureRepository.save(request);

    }

    public TestStructure loadTest(String id, String testId) {
        TestStructure testStructure = testStructureRepository.getById(testId);
        for (QuestionModel q : testStructure.getTest()) {
            q.setTrueAnswer(null);
        }
        return testStructure;
    }

    public List<String> getAllTests() {
        List<TestStructure> strings = testStructureRepository.findAll();
        List<String> testNames = new ArrayList<>();
        strings.forEach(p -> testNames.add(p.getName()));
        return testNames;
    }



}
