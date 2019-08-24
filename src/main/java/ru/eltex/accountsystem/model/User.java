package ru.eltex.accountsystem.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.eltex.accountsystem.enums.Role;

@Getter
@Setter
public abstract class User {
    private String id;
    private String login;
    private String password;
    private String email;
    private String fio;
    private Role role;

    public User(String id, String login, String password, String email, String fio, Role role) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.fio = fio;
        this.role = role;
    }
}