package ru.eltex.accountsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.eltex.accountsystem.model.TestResult;
import ru.eltex.accountsystem.repository.TestResultRepository;
import ru.eltex.testsystem.model.TestAnswers;
import ru.eltex.testsystem.model.TestStructure;
import ru.eltex.testsystem.repository.TestStructureRepository;

import java.util.ArrayList;

@Service
public class TestResultService {
    private final TestResultRepository testResultRepository;
    private final TestStructureRepository testStructureRepository;

    @Autowired
    public TestResultService(TestResultRepository testResultRepository, TestStructureRepository testStructureRepository) {
        this.testResultRepository = testResultRepository;
        this.testStructureRepository = testStructureRepository;
    }


    public void initTestResult(String id, String testId) {
        TestResult testResult = testResultRepository.getByIdAndIdTest(id, testId);
        if (testResult == null) {
            testResult = new TestResult(id, testId, 0, "0", 0, new TestAnswers(), null);
            testResultRepository.save(testResult);
        }
    }

    public void setTestCurrentAnswers(String id, String testId, TestAnswers testAnswers) {
        TestResult testResult = testResultRepository.getByIdAndIdTest(id, testId);
        testResult.setTestCurrentAnswers(testAnswers);
        testResultRepository.save(testResult);
    }

    public void setTestResult(String id, String testId, TestAnswers testAnswers) {
        TestResult testResult = testResultRepository.getByIdAndIdTest(id, testId);
        testResult.setTestFinishAnswers(testAnswers); //устанавливаем финальные ответы теста
        TestStructure testStructure = testStructureRepository.getById(testId); //загрузка структуры теста
        testResult = calculateTestResult(testStructure, testResult); // подсчет баллов за тест
        System.out.println(testResult);
        testResultRepository.save(testResult);
    }

    public TestResult calculateTestResult(TestStructure testStructure, TestResult testResult) {
        testResult.setTestCurrentAnswers(null); //обнуляем текущие ответы
        Integer result = 0;
        for (int i = 0; i < testStructure.getTest().size(); i++) { //цикл подсчета баллов за тест
            if (testStructure.getTest().get(i).getTrueAnswer()
                    .equals
                            (testResult.getTestFinishAnswers().getCheckedItems().get(i))) { //Если ответы совпадают
                result += Integer.parseInt(testStructure.getTest().get(i).getPoint());
            }
        }
        testResult.setResult(result);

        ArrayList<String> marks = new ArrayList<>();

        for (int i = 0; i < testStructure.getGraduation().size(); i++) {  //цикл определения оценки за тест
            testStructure.getGraduation().size();
            System.out.println(result);
            System.out.println(testStructure.getGraduation().get(4));
            if (result >= testStructure.getGraduation().get(i)) {
                testResult.setMark("5");
                testResult.setMark("4-");
                break;
            }

        }
        System.out.println(testResult.getMark());

        testResult.setTimeOfTest(123); //ДОПИСАТЬ ПОДСЧЕТ ВРЕМЕНИ ТЕСТИРОВАНИЯ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        return testResult;
    }

    public TestResult getTestResult(String id, String testId) {
        TestResult testResult = testResultRepository.getByIdAndIdTest(id, testId);
        return testResult;
    }

}
