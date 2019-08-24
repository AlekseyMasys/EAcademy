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

    @RequestMapping(value = "/teacher/{id}/subject/{idSubject}/createTest", method = RequestMethod.POST, produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public void saveTest(@PathVariable("id") String id, @PathVariable("idSubject") String idSubject, @RequestBody TestStructure request) {
        testStructureService.saveTest(request, id, idSubject);
        return;
    }

    /* @PostMapping("/node/api/objects/{id}/stat")
    public String getStat(@PathVariable String id,
                              @RequestBody ControllerCurrentStatus stat) {*/
    @RequestMapping(value = "/load_test", method = RequestMethod.POST)
    @ResponseBody
    public TestStructure loadTest(@RequestBody JsonNode request) {

        return testStructureService.loadTest(request);
    }

    @RequestMapping(value = "/get_all_tests", method = RequestMethod.GET)
    public List<java.lang.String> getAllTests() {

        return testStructureService.getAllTests();
    }
}