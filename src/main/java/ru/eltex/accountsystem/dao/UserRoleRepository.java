package ru.eltex.accountsystem.dao;


import org.springframework.data.mongodb.repository.MongoRepository;
import ru.eltex.accountsystem.model.UserRole;

public interface UserRoleRepository extends MongoRepository<UserRole, String> {
}
