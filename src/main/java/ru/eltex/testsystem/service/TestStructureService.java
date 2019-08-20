package ru.eltex.testsystem.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.eltex.testsystem.model.TestStructure;
import ru.eltex.testsystem.repository.TestStructureRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class TestStructureService {
private final TestStructureRepository testStructureRepository;
private final ObjectMapper objectMapper;
    @Autowired
    public TestStructureService(TestStructureRepository testStructureRepository, ObjectMapper objectMapper) {
        this.testStructureRepository = testStructureRepository;
        this.objectMapper = objectMapper;
    }
    public void saveTest(TestStructure request){
        testStructureRepository.save(request);
    }
    public TestStructure loadTest(JsonNode request){
        Map<String, String> result = objectMapper.convertValue(request, Map.class);
       return testStructureRepository.getByName(result.get("name"));
    }
    public List<String> getAllTests(){
        List<TestStructure> testStructures=testStructureRepository.findAll();
        List<String> testNames= new ArrayList<>();
        testStructures.forEach(p->testNames.add(p.getName()));
        return testNames;
    }
}
