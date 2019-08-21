package ru.eltex.accountsystem.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class SubjectRepositoryCustomImpl {
    @Autowired
    private MongoTemplate mongoTemplate;
}
