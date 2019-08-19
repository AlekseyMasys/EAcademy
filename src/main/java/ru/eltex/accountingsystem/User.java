package ru.eltex.accountingsystem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class User {
    private Integer id;
    private String login;
    private String password;
    private String email;
    private String fio;
    private String status;
}
