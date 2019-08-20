package ru.eltex.accountingsystem.documents;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@NoArgsConstructor
@Document(collection = "Groups")
public class Group {
    @Id
    private int id;
    private String title;
    private ArrayList<Student> students;

    public Group(String title, ArrayList<Student> students) {
        this.title = title;
        this.students = students;
    }

}
