package ru.eltex.accountsystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.eltex.accountsystem.model.UserRole;

public interface AllUserRepository extends MongoRepository<UserRole, String> {
    UserRole findByUserLoginAndUserPassword(String userLogin, String userPassword);
}
