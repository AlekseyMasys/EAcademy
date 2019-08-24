package ru.eltex.accountsystem.model.users;

import org.springframework.data.mongodb.core.mapping.Document;
import ru.eltex.accountsystem.enums.Role;
import ru.eltex.accountsystem.model.User;

import java.util.ArrayList;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "teachers")
public class Teacher extends User {
    private ArrayList<String> subjects;

    public Teacher(String login, String password, String email,
                   String fio, Role role,
                   ArrayList<String> subjects) {
        super(login, password, email, fio, role);

        this.subjects = subjects;
    }

    public Teacher(Map<String, String> user, ArrayList<String> subjects) {
        super(user.get("login"), user.get("password"), user.get("email"), user.get("fio"), Role.valueOf(user.get("role")));
        this.subjects = subjects;
    }
}
