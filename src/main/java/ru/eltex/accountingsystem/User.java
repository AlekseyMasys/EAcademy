package ru.eltex.accountingsystem;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import ru.eltex.accountingsystem.enums.Role;

@Getter
@Setter
public abstract class User {
    @Id
    private Integer id;
    private String login;
    private String password;
    private String email;
    private String fio;
    private Role role;

    public User(String login, String password, String email, String fio, Role role) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.fio = fio;
        this.role = role;
    }
}
