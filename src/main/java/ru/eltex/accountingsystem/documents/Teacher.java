package ru.eltex.accountingsystem.documents;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.eltex.accountingsystem.User;

@Getter
@Setter
@Document(collection = "teachers")
public class Teacher extends User {
    private String[] groups;

    public Teacher(Integer id, String login, String password, String email,
                   String fio, String status,
                   String[] groups) {
        super(id, login, password, email, fio, status);
        this.groups = groups;
    }
}
