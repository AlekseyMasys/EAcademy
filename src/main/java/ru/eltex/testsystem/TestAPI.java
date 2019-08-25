package ru.eltex.testsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.eltex.testsystem.model.TestAnswers;
import ru.eltex.testsystem.service.TestStructureService;

@Controller
public class TestAPI {
    private final TestStructureService testStructureService;

    @Autowired
    public TestAPI(TestStructureService testStructureService) {
        this.testStructureService = testStructureService;
    }

    @GetMapping(value = "/index")
    public String index() {
        return "bootstrap4/file_upload";
    }


    @RequestMapping(value = "/student/{id}/subject/{idSubject}/{idTest}", method = RequestMethod.GET)
    public String showtest(Model model, @PathVariable("id") String id, @PathVariable("idSubject") String idSubject, @PathVariable("idTest") String idTest) {
        model.addAttribute("testmodel", testStructureService.loadTest(id, idSubject, idTest));
        model.addAttribute("testAnswers",new TestAnswers());
        return "showTest";
    }


    @PostMapping("/testresult")
    public String getDataTest(Model model, @ModelAttribute("testAnswers") TestAnswers testAnswers) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!getDataTest");
        System.out.println(testAnswers.toString());
        return "help";
    }


}
