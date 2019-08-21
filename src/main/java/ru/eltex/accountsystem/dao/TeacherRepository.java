package ru.eltex.accountsystem.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.eltex.accountsystem.model.users.Teacher;
import java.util.List;

public interface TeacherRepository extends MongoRepository<Teacher, Integer> {
    List<Teacher> findById(String id);
}
