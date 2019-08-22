package ru.eltex.testsystem.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import ru.eltex.testsystem.model.TestStructure;

@Service
public class TestStructureRepositoryCustomImpl implements TestStructureRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    public TestStructure getByName(java.lang.String name){
        return mongoTemplate.findOne(Query.query(Criteria.where("name").regex(name)), TestStructure.class);
    }

//    public TestStructure getByName(String name){
//        return mongoTemplate.findOne(Query.query(Criteria.where("name").regex(name)), TestStructure.class);
//    }
}
