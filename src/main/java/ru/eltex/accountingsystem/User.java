package ru.eltex.accountingsystem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
public abstract class User {
    @Id
    private Integer id;
    private String login;
    private String password;
    private String email;
    private String fio;
    private Status status;
}
