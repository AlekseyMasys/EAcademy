package ru.eltex.accountsystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Document(collection = "subjects")
public class Subject {
    private String id;

    @Override
    public String toString() {
        return "Subject{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", taskIds=" + taskIds +
                ", groupIds=" + groupIds +
                ", testIds=" + testIds +
                '}';
    }

    private String title;
    private List<String> taskIds;
    private List<String> groupIds;
    private List<String> testIds;

    public Subject() {
        this.id= UUID.randomUUID().toString();
    }

    public Subject(String title, List<String> taskIds, List<String> groupIds, List<String> testIds) {
        this.title = title;
        this.taskIds = taskIds;
        this.groupIds = groupIds;
        this.testIds = testIds;
    }
}