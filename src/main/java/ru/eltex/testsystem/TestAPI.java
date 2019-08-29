package ru.eltex.testsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.eltex.accountsystem.service.TestResultService;
import ru.eltex.testsystem.model.TestAnswers;
import ru.eltex.testsystem.service.TestStructureService;

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
        model.addAttribute("testmodel", testStructureService.loadTest(id, testId));
        testResultService.initTestResult(id,testId); // Если в БД нет Такого TestResult то создаем его в БД
        TestAnswers testCurrentAnswers = testResultService.getTestResult(id,testId).getTestCurrentAnswers();
        System.out.println(testCurrentAnswers);
        if(testResultService.getTestResult(id,testId).getTestFinishAnswers()!=null) {
            model.addAttribute("testResult", testResultService.getTestResult(id, testId));
            return "test_alredy_done";
        }
        model.addAttribute("testAnswers",testCurrentAnswers);
        return "showTest";
    }

    @RequestMapping(value = "/student/{id}/{testId}/saveCurrentResult", method = RequestMethod.POST)
    @ResponseBody
    public void addGroup(@RequestBody String testCurrentAnswers, @PathVariable("id") String id
            ,@PathVariable("testId") String idTest) {
        System.out.println(testCurrentAnswers);
        System.out.println("Внести в БД текущие данные теста");
//        testResultService.setTestCurrentAnswers(id,idTest,testCurrentAnswers);
    }

    @PostMapping("/student/{id}/{testId}/finishtest")
    public String getDataTest(Model model, @ModelAttribute("testAnswers") TestAnswers testAnswers,
                              @PathVariable("id") String id,  @PathVariable("testId") String testId) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!  ТЕСТ ЗАВЕРШЕН  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(testAnswers.toString());
        testResultService.setTestResult(id,testId,testAnswers);
        return "test_result";
    }

}
