package ru.eltex.accountingsystem.documents;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.eltex.accountingsystem.enums.Status;
import ru.eltex.accountingsystem.User;

@Getter
@Setter
@Document(collection = "teachers")
public class Teacher extends User {
    private String[] groups;
    private String[] subjects;

    public Teacher(String login, String password, String email,
                   String fio, Status status,
                   String[] groups, String[] subjects) {
        super(login, password, email, fio, status);

        this.groups = groups;
        this.subjects = subjects;
    }
}
