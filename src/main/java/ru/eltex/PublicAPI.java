package ru.eltex;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.eltex.testsystem.service.TestStructureService;

import java.util.List;

@Controller
public class PublicAPI {
    private final TestStructureService testStructureService;
    @Autowired
    public PublicAPI(TestStructureService testStructureService) {
        this.testStructureService = testStructureService;
    }
    @RequestMapping(value = "/save_test",method = RequestMethod.POST)
    @ResponseBody
    public void SaveTest(@RequestBody JsonNode request) {
        System.out.println(request.toString());
        testStructureService.saveTest(request);
        return;
    }
}
