package ru.eltex.accountingsystem;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "students")
public class Student extends User{

}
