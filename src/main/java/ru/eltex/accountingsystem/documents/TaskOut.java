package ru.eltex.accountingsystem.documents;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "taskout")
public class TaskOut {
    public TaskOut(String title, String description, Integer maxScores, String answer, Integer scores, Boolean done){
        this.title = title;
        this.description = description;
        this.maxScores = maxScores;
        this.answer = answer;
        this.scores = scores;
        this.done = done;
    }

    @Id
    private Integer id;
    private String title;
    private String description;
    private Integer maxScores;
    private String answer;
    private Integer scores;
    private Boolean done;
}
