package ru.eltex.testsystem.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class TestStructureRepositoryCustomImpl implements TestStructureRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;
}
