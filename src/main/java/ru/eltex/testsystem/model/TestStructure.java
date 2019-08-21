package ru.eltex.testsystem.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Getter
@Setter
@Document(collection = "teststructure")
public class TestStructure {
    @Id
    private String id;
    private String name;
    private String type;
    private ArrayList<QuestionModel> test;

    private Integer score;
}
