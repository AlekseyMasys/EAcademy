package ru.eltex.accountsystem.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.eltex.accountsystem.model.users.Teacher;
@Repository
public interface TeacherRepository extends MongoRepository<Teacher, String>,TeacherRepositoryCustom {

}
