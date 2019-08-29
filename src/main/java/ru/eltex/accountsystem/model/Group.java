package ru.eltex.accountsystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.eltex.accountsystem.model.users.Student;

import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "groups")
public class Group {
    private String id;
    private String title;
    private String table;
    private ArrayList<String> studentIds;

    public Group(String title, ArrayList<String> studentIds) {
        id = UUID.randomUUID().toString();
        this.title = title;
        this.studentIds = studentIds;
    }

    public Group(String title, String table, ArrayList<String> studentIds) {
        id = UUID.randomUUID().toString();
        this.title = title;
        this.table = table;
        this.studentIds = studentIds;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getTable() {
        return table;
    }
}