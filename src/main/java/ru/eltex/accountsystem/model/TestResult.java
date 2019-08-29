package ru.eltex.accountsystem.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.eltex.testsystem.model.TestAnswers;

@Getter
@Setter
@Document(collection = "testResult")
public class TestResult {
    @Id
    private String id;
    private String studentId;
    private String testId;
    private Integer result;
    private Integer timeOfTest;
    private TestAnswers testCurrentAnswers;
    private TestAnswers testFinishAnswers;


    public TestResult(String studentId, String testId, Integer result, Integer timeOfTest) {
        this.studentId = studentId;
        this.testId = testId;
        this.result = result;
        this.timeOfTest = timeOfTest;
    }

}
