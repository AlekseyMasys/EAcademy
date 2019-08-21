package ru.eltex.accountsystem.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "task")
public class Task {
    @Id
    private String id;
    private String title;
    private String description;
    private Integer maxScores;
    private String answer;
    private Integer scores;

    public Task(String title, String description, Integer maxScores, String answer, Integer scores){
        this.title = title;
        this.description = description;
        this.maxScores = maxScores;
        this.answer = answer;
        this.scores = scores;
    }
}