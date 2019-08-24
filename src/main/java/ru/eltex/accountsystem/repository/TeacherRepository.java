package ru.eltex.accountsystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.eltex.accountsystem.dao.TeacherRepositoryCustom;
import ru.eltex.accountsystem.model.users.Teacher;

public interface TeacherRepository extends MongoRepository<Teacher, String>, TeacherRepositoryCustom {

}
