package ru.eltex.accountingsystem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class User {
    private Integer id;
    private String login;
    private String password;
    private String email;
    private String fio;
    private String status;


}
