package ru.eltex.testsystem.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.eltex.accountsystem.dao.TeacherRepository;
import ru.eltex.accountsystem.model.Subject;
import ru.eltex.accountsystem.model.users.Teacher;
import ru.eltex.testsystem.model.TestStructure;
import ru.eltex.testsystem.repository.TestStructureRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TestStructureService {
    private final TestStructureRepository testStructureRepository;
    private final ObjectMapper objectMapper;
    private final TeacherRepository teacherRepository;


    @Autowired
    public TestStructureService(TestStructureRepository testStructureRepository, ObjectMapper objectMapper, TeacherRepository teacherRepository) {
        this.testStructureRepository = testStructureRepository;
        this.objectMapper = objectMapper;
        this.teacherRepository = teacherRepository;
    }

    public void saveTest(TestStructure request, String id, String idSubject) {
        Teacher teacher = teacherRepository.findTeacherById(id);
        List<Subject> subjects = teacher.getSubjects();
        subjects.stream().forEach(n ->{
            if (n.getTitle().equals(idSubject)){
                List<String> testNames = n.getTests();
                testNames.add(request.getId());
            }
        });
        teacherRepository.save(teacher);
        testStructureRepository.save(request);

    }

    public TestStructure loadTest(JsonNode request) {
        Map<String, String> result = objectMapper.convertValue(request, Map.class);
        return testStructureRepository.getByName(result.get("name"));
    }

    public List<String> getAllTests() {
        List<TestStructure> strings = testStructureRepository.findAll();
        List<String> testNames = new ArrayList<>();
        strings.forEach(p -> testNames.add(p.getName()));
        return testNames;
    }
}
