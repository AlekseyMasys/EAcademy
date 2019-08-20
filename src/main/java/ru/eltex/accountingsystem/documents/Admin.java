package ru.eltex.accountingsystem.documents;

import org.springframework.data.mongodb.core.mapping.Document;
import ru.eltex.accountingsystem.enums.Role;
import ru.eltex.accountingsystem.User;

@Document(collection = "admins")
public class Admin extends User {
    public Admin(String login, String password, String email,
                 String fio, Role role) {
        super(login, password, email, fio, role);
    }
}
