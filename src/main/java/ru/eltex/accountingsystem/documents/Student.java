package ru.eltex.accountingsystem.documents;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.eltex.accountingsystem.enums.Role;
import ru.eltex.accountingsystem.TestResult;
import ru.eltex.accountingsystem.User;

import java.util.ArrayList;

@Getter
@Setter
@Document(collection = "students")
public class Student extends User {
    Integer group;
    ArrayList<TestResult> tests;

    public Student(String login, String password,
                   String email, String fio, Role role,
                   Integer group, ArrayList<TestResult> tests){
        super(login,password,email, fio, role);

        this.group = group;
        this.tests = tests;
    }
}
