package ru.eltex.accountsystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.eltex.accountsystem.model.Task;

public interface TaskRepository extends MongoRepository<Task, String> {
}
