package ru.eltex.accountsystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.eltex.accountsystem.dao.SubjectRepositoryCustom;
import ru.eltex.accountsystem.model.Subject;

@Repository
public interface SubjectRepository extends MongoRepository<Subject, String>, SubjectRepositoryCustom {
}
