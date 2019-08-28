package ru.eltex.accountsystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.eltex.accountsystem.model.users.Student;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "groups")
public class Group {
    @Id
    private String id;
    private String title;
    private String table;
    private ArrayList<Student> students;

    public Group(String title, ArrayList<Student> students) {
        this.title = title;
        this.students = students;
    }

    public Group(String title, String table, ArrayList<Student> students) {
        this.title = title;
        this.table = table;
        this.students = students;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getTable() {
        return table;
    }
}