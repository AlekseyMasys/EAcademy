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
    private List<String> taskIds;
    private List<String> groupIds;
    private List<String> testIds;

    public Subject(String title, List<String> taskIds, List<String> groupIds, List<String> testIds) {
        this.title = title;
        this.taskIds = taskIds;
        this.groupIds = groupIds;
        this.testIds = testIds;
    }
}