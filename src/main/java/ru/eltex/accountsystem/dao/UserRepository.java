package ru.eltex.accountsystem.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.eltex.accountsystem.model.User;

import java.util.List;

public interface UserRepository extends MongoRepository<User, Integer> {
    List<User> findByFio(String fio);
}
