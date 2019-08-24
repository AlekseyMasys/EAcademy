package ru.eltex.accountsystem.model.users;

import org.springframework.data.mongodb.core.mapping.Document;
import ru.eltex.accountsystem.enums.Role;
import ru.eltex.accountsystem.model.User;

@Document(collection = "admins")
public class Admin extends User {
    public Admin(String id, String login, String password, String email,
                 String fio, Role role) {
        super(id, login, password, email, fio, role);
    }
}
