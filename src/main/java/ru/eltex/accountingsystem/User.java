package ru.eltex.accountingsystem;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import ru.eltex.accountingsystem.enums.Status;

@Getter
@Setter
public abstract class User {
    @Id
    private Integer id;
    private String login;
    private String password;
    private String email;
    private String fio;
    private Status status;

    public User(String login, String password, String email, String fio, Status status) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.fio = fio;
        this.status = status;
    }
}
