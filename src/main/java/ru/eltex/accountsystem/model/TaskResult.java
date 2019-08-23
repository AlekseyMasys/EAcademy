package ru.eltex.accountsystem.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "taskResult")
public class TaskResult{
    @Id
    private String id;
    private String idTask;
    private String idStudent;
    private String answer;
    private Integer scores;
    private String status;

    public TaskResult(String idTeacherTask, String idStudent, String answer, Integer scores, String status) {
        this.idTask = idTeacherTask;
        this.idStudent = idStudent;
        this.answer = answer;
        this.scores = scores;
        this.status = status;
    }
}