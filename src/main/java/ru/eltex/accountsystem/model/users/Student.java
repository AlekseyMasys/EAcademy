package ru.eltex.accountsystem.model.users;

import org.springframework.data.mongodb.core.mapping.Document;
import ru.eltex.accountsystem.enums.Role;
import ru.eltex.accountsystem.model.User;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "students")
public class Student extends User {
    ArrayList<String> subjects;
    String groupId;

    public Student(String login, String password,
                   String email, String fio, Role role,
                   ArrayList<String> subjects){
        super(login,password,email, fio, role);

        this.subjects = subjects;
    }
}
