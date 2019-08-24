package ru.eltex.accountsystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.eltex.accountsystem.model.users.Admin;

public interface AdminRepository extends MongoRepository<Admin, String> {
}
