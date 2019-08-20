package ru.eltex.accountsystem.model.users;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.mongodb.core.mapping.Document;
import ru.eltex.accountsystem.enums.Role;
import ru.eltex.accountsystem.model.User;

import javax.security.auth.Subject;
import java.util.ArrayList;

@Getter
@Setter
@Document(collection = "students")
public class Student extends User {
    ArrayList<Subject> subjects;

    public Student(String login, String password,
                   String email, String fio, Role role,
                   ArrayList<Subject> subjects){
        super(login,password,email, fio, role);

        this.subjects = subjects;
    }
}
