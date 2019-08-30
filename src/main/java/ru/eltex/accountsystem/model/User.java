package ru.eltex.accountsystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.eltex.accountsystem.enums.Role;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public abstract class User {
    private String id;
    private String login;
    private String password;
    private String email;
    private String fio;
    private Role role;

    public User(String login, String password, String email, String fio, Role role) {
        this.id = UUID.randomUUID().toString();
        this.login = login;
        this.password = password;
        this.email = email;
        this.fio = fio;
        this.role = role;
    }
}