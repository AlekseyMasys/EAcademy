package ru.eltex.testsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.eltex.testsystem.model.TestAnswers;
import ru.eltex.testsystem.service.TestStructureService;

import java.util.ArrayList;
import java.util.List;

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


    @RequestMapping(value = "/student/{id}/{idTest}", method = RequestMethod.GET)
    public String showtest(Model model, @PathVariable("id") String id,  @PathVariable("idTest") String idTest) {
        model.addAttribute("testmodel", testStructureService.loadTest(id, idTest));
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        List<List<String>> list2 = new ArrayList<>();
        list2.add(list);
        TestAnswers testAnswers = new TestAnswers();
        testAnswers.setCheckedItems(list2);
        model.addAttribute("testAnswers",testAnswers);
        System.out.println(testAnswers.toString());

        return "showTest";
    }

//    @PostMapping("/student/{id}/{idTest}/saveCurrentResult") сохранить в текущий


    @PostMapping("/student/{id}/{idTest}/finishtest")
    public String getDataTest(Model model, @ModelAttribute("testAnswers") TestAnswers testAnswers) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!getDataTest");
        System.out.println(testAnswers.toString());
        return "help";
    }


}
