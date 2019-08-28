package ru.eltex.api;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.eltex.testsystem.model.TestStructure;
import ru.eltex.testsystem.service.TestStructureService;

import java.util.List;

@RestController
public class TestController {
    private final TestStructureService testStructureService;

    @Autowired
    public TestController(TestStructureService testStructureService) {
        this.testStructureService = testStructureService;
    }

    @RequestMapping(value = "/teacher/{teacherId}/subject/{idSubject}/createTest", method = RequestMethod.POST, produces = {"application/json; charset=UTF-8"})
    public void saveTest(@PathVariable("teacherId") java.lang.String id, @PathVariable("idSubject") java.lang.String idSubject, @RequestBody TestStructure request) {
        testStructureService.saveTest(request, id, idSubject);
    }

    @RequestMapping(value = "/loadTest", method = RequestMethod.POST)
    public TestStructure loadTest(@RequestBody JsonNode request) {
        return testStructureService.loadTest(request);
    }

    @RequestMapping(value = "/getAllTests", method = RequestMethod.GET)
    public List<java.lang.String> getAllTests() {
        return testStructureService.getAllTests();
    }
}