package ru.eltex.testsystem.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.UUID;

@Getter@Setter
@Document(collection = "teststructure")
public class TestStructure {

    private String id;
    private String name;
    private ArrayList<Integer> graduation;
    private String type;
    private String repassing;
    private String someAnswers;
    private String timelimite;
    private ArrayList<QuestionModel> test;

    public TestStructure() {
        this.id = UUID.randomUUID().toString();
    }
}
