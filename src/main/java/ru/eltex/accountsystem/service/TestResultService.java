package ru.eltex.accountsystem.service;

import org.springframework.stereotype.Service;
import ru.eltex.accountsystem.model.TestResult;
import ru.eltex.accountsystem.repository.TestResultRepository;
import ru.eltex.testsystem.model.TestAnswers;
@Service
public class TestResultService {
    private final TestResultRepository testResultRepository;

    public TestResultService(TestResultRepository testResultRepository) {
        this.testResultRepository = testResultRepository;
    }

    public void initTestResult(String id, String testId) {
        TestResult testResult = testResultRepository.getByIdAndIdTest(id, testId);
        if (testResult == null) {
            testResult = new TestResult(id, testId, null, null);
            testResultRepository.save(testResult);
        }
    }

    public void setTestCurrentResult(String id, String testId, TestAnswers testAnswers) {
        TestResult testResult = testResultRepository.getByIdAndIdTest(id, testId);
        testResult.setTestCurrentAnswers(testAnswers);
        testResultRepository.save(testResult);
    }
}
