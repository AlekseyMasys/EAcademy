package ru.eltex.testsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.eltex.testsystem.service.TestStructureService;

@Controller
public class PublicAPI {
    private final TestStructureService testStructureService;
    @Autowired
    public PublicAPI(TestStructureService testStructureService) {
        this.testStructureService = testStructureService;
    }

}
