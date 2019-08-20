package ru.eltex.testsystem.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.eltex.testsystem.repository.TestStructureRepository;

public class TestStructureService {
private final TestStructureRepository testStructureRepository;
private final ObjectMapper objectMapper;
    @Autowired
    public TestStructureService(TestStructureRepository testStructureRepository, ObjectMapper objectMapper) {
        this.testStructureRepository = testStructureRepository;
        this.objectMapper = objectMapper;
    }
    public void saveTest(JsonNode request){

    }

}
