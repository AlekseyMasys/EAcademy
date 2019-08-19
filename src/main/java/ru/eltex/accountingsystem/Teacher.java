package ru.eltex.accountingsystem;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "teachers")
public class Teacher extends User {
    private String[] groups;
}
