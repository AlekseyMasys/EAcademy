package ru.eltex.accountsystem.model.users;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.eltex.accountsystem.enums.Role;
import ru.eltex.accountsystem.model.User;
import ru.eltex.accountsystem.model.Subject;

import java.util.ArrayList;

@Getter
@Setter
@Document(collection = "teachers")
public class Teacher extends User {
    private ArrayList<Subject> subjects;

    public Teacher(String login, String password, String email,
                   String fio, Role role,
                   ArrayList<Subject> subjects) {
        super(login, password, email, fio, role);

        this.subjects = subjects;
    }
}
