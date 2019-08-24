package ru.eltex.accountsystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.eltex.accountsystem.model.Group;

public interface GroupRepository extends MongoRepository<Group, String> {
}
