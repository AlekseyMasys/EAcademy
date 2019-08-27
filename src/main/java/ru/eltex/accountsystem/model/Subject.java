package ru.eltex.accountsystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "subjects")
public class Subject {
    @Id
    private String id;
    private String title;
    private List<String> tasksId;
    private List<Group> groups;
    private List<String> testsId;

    public Subject(String title, List<String> tasksId, List<Group> groups, List<String> testsId) {
        this.title = title;
        this.tasksId = tasksId;
        this.groups = groups;
        this.testsId = testsId;
    }
}