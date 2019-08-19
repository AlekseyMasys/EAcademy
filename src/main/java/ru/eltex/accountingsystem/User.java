package ru.eltex.accountingsystem;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "users")
public class User {
    private Integer id;
    private String login;
    private String password;
    private String fio;
}
