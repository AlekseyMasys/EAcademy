package ru.eltex.accountsystem.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "taskteacher")
public class TeacherTask extends Task{
    private String title;
    private String description;
    private Integer maxScores;

    public TeacherTask(String title, String description, Integer maxScores){
        this.title = title;
        this.description = description;
        this.maxScores = maxScores;
    }
}
