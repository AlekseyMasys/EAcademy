package ru.eltex.accountingsystem;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, Integer> {
    List<User> findByFio(String fio);
}
