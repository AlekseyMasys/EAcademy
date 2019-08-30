package ru.eltex.testsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.eltex.accountsystem.service.TestResultService;
import ru.eltex.testsystem.model.TestAnswers;
import ru.eltex.testsystem.service.TestStructureService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestAPI {
    private final TestStructureService testStructureService;
    private final TestResultService testResultService;

    @Autowired
    public TestAPI(TestStructureService testStructureService, TestResultService testResultService) {
        this.testStructureService = testStructureService;
        this.testResultService = testResultService;
    }

    @GetMapping(value = "/index")
    public String index() {
        return "bootstrap4/file_upload";
    }

    @RequestMapping(value = "/student/{id}/{testId}", method = RequestMethod.GET)
    public String showtest(Model model, @PathVariable("id") String id,  @PathVariable("testId") String testId) {
        model.addAttribute("testmodel", testStructureService.loadTest(testId));
        testResultService.initTestResult(id,testId);

//      Если в БД нет Такого TestResult то создаем его в БД

        List<String> list = new ArrayList<>();
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

    @PostMapping("/student/{id}/{testId}/finishtest")
    public String getDataTest( @ModelAttribute("testAnswers") TestAnswers testAnswers) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!  ТЕСТ ЗАВЕРШЕН  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(testAnswers.toString());

        return "help";
    }

    @RequestMapping(value = "/student/{id}/{testId}/saveCurrentResult", method = RequestMethod.POST)
    @ResponseBody
    public void addGroup(@RequestBody TestAnswers testAnswers, @PathVariable("id") String id,  @PathVariable("idTest") String idTest) {
        System.out.println("Внести в БД текущие данные теста");
        testResultService.setTestCurrentResult(id,idTest,testAnswers  );
    }
}