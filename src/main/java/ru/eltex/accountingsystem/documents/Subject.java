package ru.eltex.accountingsystem.documents;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@NoArgsConstructor
@Document(collection = "subjects")
public class Subject {
    @Id
    private Integer id;
    private String title;
    private ArrayList<Task> tasks;
    private ArrayList<Group> groups;

    public Subject(String title, ArrayList<Task> tasks, ArrayList<Group> groups) {
        this.title = title;
        this.tasks = tasks;
        this.groups = groups;
    }
}
