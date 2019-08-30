package ru.eltex.accountsystem.model.users;

import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.eltex.accountsystem.enums.Role;
import ru.eltex.accountsystem.model.User;

import java.util.ArrayList;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * Класс представления студента
 * @author Maria Koloskova
 * @version v2.0
 */
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "students")
public class Student extends User {
    /** Поле идентификаторов дисциплин */
    ArrayList<String> subjects;

    /** Поле идентификатора группы*/
    String groupId;

    public Student(String login, String password,
                   String email, String fio, Role role,
                   ArrayList<String> subjects) {
        super(login, password, email, fio, role);

        this.subjects = subjects;
    }

    public Student(Map<String, String> user, ArrayList<String> subjects, String groupId) {
        super(user.get("login"), user.get("password"), user.get("email"), user.get("fio"), Role.valueOf(user.get("role")));
        this.subjects = subjects;
        this.groupId = groupId;
    }
}
