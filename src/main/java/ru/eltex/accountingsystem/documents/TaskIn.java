package ru.eltex.accountingsystem.documents;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "taskin")
public class TaskIn {
    public TaskIn(String title, String description, Integer maxScores){
        this.title = title;
        this.description = description;
        this.maxScores = maxScores;
    }

    @Id
    private Integer id;
    private String title;
    private String description;
    private Integer maxScores;
}