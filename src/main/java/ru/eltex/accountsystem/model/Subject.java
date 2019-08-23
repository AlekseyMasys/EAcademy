package ru.eltex.accountsystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "subjects")
public class Subject {
    @Id
    private String id;
    private String title;
    private ArrayList<String> tasks;
    private ArrayList<Group> groups;
    private ArrayList<String> tests;

    public Subject(String title, ArrayList<String> tasks, ArrayList<Group> groups, ArrayList<String> tests) {
        this.title = title;
        this.tasks = tasks;
        this.groups = groups;
        this.tests = tests;
    }
}