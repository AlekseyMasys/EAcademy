package ru.eltex.accountsystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.eltex.accountsystem.model.TestResult;

public interface TestResultRepository extends MongoRepository<TestResult, String> {
    TestResult findByTestId(String testId);
}
