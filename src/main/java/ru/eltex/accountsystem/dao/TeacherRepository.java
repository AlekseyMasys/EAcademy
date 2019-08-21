package ru.eltex.accountsystem.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.eltex.accountsystem.model.users.Teacher;

public interface TeacherRepository extends MongoRepository<Teacher, String> {
}
