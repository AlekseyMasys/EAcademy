package ru.eltex.accountsystem.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
@Document(collection = "table")
public class Table {
    @Id
    private String id;
    private String time;
    private ArrayList<ArrayList<String>> dayWeek;

    public Table() {
        this.id = UUID.randomUUID().toString();
    }
}

