package ru.eltex.accountsystem.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.eltex.accountsystem.model.TaskResult;

public interface TaskResultRepository extends MongoRepository<TaskResult, String> {
    TaskResult findByIdTask(String idTask);
}