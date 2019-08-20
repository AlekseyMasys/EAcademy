package ru.eltex.accountingsystem.documents;

import org.springframework.data.mongodb.core.mapping.Document;
import ru.eltex.accountingsystem.enums.Status;
import ru.eltex.accountingsystem.User;

@Document(collection = "admins")
public class Admin extends User {
    public Admin(String login, String password, String email,
                 String fio, Status status) {
        super(login, password, email, fio, status);
    }
}
