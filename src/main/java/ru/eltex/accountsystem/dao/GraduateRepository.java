package ru.eltex.accountsystem.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.eltex.accountsystem.model.users.Graduate;

public interface GraduateRepository extends MongoRepository<Graduate, String> {
}
