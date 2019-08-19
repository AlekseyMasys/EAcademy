package ru.eltex.accountingsystem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "students")
public class Student extends User{
    Integer group;
    ArrayList<TestResult> tests;
}
