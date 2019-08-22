package ru.eltex.accountsystem.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "studenttask")
public class StudentTask extends Task{
    private String idTeacherTask;
    private String answer;
    private Integer scores;

    public StudentTask(String answer, Integer scores, String idTask) {
        this.answer = answer;
        this.scores = scores;
        this.idTeacherTask = idTask;
    }
}