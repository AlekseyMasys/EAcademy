package ru.eltex.accountingsystem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Getter
@Setter

@Document(collection = "students")
public class Student extends User{
    Integer group;
    ArrayList<TestResult> tests;

    public Student(Integer id, String login, String password,
                   String email, String fio, String status,
                   Integer group, ArrayList<TestResult> tests){
        super(id,login,password,email, fio, status);
        this.group = group;
        this.tests = tests;
    }
}
