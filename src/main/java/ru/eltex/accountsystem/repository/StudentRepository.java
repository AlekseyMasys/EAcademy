package ru.eltex.accountsystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.eltex.accountsystem.model.users.Student;
import java.util.List;

public interface StudentRepository extends MongoRepository<Student, String> {
}
