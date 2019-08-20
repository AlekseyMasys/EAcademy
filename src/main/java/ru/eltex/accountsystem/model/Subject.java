package ru.eltex.accountsystem.model;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.eltex.testsystem.model.TestStructure;

import java.util.ArrayList;

@NoArgsConstructor
@Document(collection = "subjects")
public class Subject {
    @Id
    private Integer id;
    private String title;
    private ArrayList<Task> tasks;
    private ArrayList<Group> groups;
    private ArrayList<TestStructure> tests;

    public Subject(String title, ArrayList<Task> tasks, ArrayList<Group> groups, ArrayList<TestStructure> tests) {
        this.title = title;
        this.tasks = tasks;
        this.groups = groups;
        this.tests = tests;
    }
}
