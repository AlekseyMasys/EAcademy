package ru.eltex;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.eltex.testsystem.model.TestStructure;
import ru.eltex.testsystem.service.TestStructureService;

import java.util.List;

@RestController
public class PublicAPI {
    private final TestStructureService testStructureService;
    @Autowired
    public PublicAPI(TestStructureService testStructureService) {
        this.testStructureService = testStructureService;
    }
    @RequestMapping(value = "/save_test",method = RequestMethod.POST)
    @ResponseBody
    public void saveTest(@RequestBody TestStructure request) {
        testStructureService.saveTest(request);
        return;
    }
    /* @PostMapping("/node/api/objects/{id}/stat")
    public String getStat(@PathVariable String id,
                              @RequestBody ControllerCurrentStatus stat) {*/
    @RequestMapping(value = "/load_test",method = RequestMethod.POST)
    @ResponseBody
    public TestStructure loadTest(@RequestBody JsonNode request) {

        return testStructureService.loadTest(request);
    }
    @RequestMapping(value = "/get_all_tests",method = RequestMethod.GET)
    public List<String> getAllTests(){

        return testStructureService.getAllTests();
    }
}