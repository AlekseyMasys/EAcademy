package ru.eltex.accountsystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.eltex.accountsystem.model.Subject;


public interface SubjectRepository extends MongoRepository<Subject, String> {
}
