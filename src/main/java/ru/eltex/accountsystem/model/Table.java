package ru.eltex.accountsystem.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Getter
@Setter
@Document(collection = "table")
public class Table {
    private String time;
    private ArrayList<ArrayList<String>> dayWeek;
}
