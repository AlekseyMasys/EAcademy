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
    private String mark;
    private Integer timeOfTest;
    private TestAnswers testCurrentAnswers;
    private TestAnswers testFinishAnswers;

    public TestResult(String studentId, String testId, Integer result, String mark, Integer timeOfTest, TestAnswers testCurrentAnswers, TestAnswers testFinishAnswers) {
        this.studentId = studentId;
        this.testId = testId;
        this.result = result;
        this.timeOfTest = timeOfTest;
        this.testCurrentAnswers = testCurrentAnswers;
        this.testFinishAnswers = testFinishAnswers;
    }

    @Override
    public String toString() {
        return "TestResult{" +
                "id='" + id + '\'' +
                ", studentId='" + studentId + '\'' +
                ", testId='" + testId + '\'' +
                ", result=" + result +
                ", timeOfTest=" + timeOfTest +
                ", testCurrentAnswers=" + testCurrentAnswers +
                ", testFinishAnswers=" + testFinishAnswers +
                '}';
    }
}
