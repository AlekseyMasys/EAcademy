package ru.eltex.accountsystem.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@Document(collection = "taskTeacher")
public class Task {
    private String id;
    private String title;
    private String description;
    private Integer maxScores;

    public Task() {
        id = UUID.randomUUID().toString();
    }

    public Task(String title, String description, Integer maxScores) {
        id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.maxScores = maxScores;
    }
}