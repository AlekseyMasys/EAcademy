package ru.eltex.accountsystem.model.users;

import org.springframework.data.mongodb.core.mapping.Document;
import ru.eltex.accountsystem.enums.Sex;
import ru.eltex.accountsystem.enums.Role;
import ru.eltex.accountsystem.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "graduates")
public class Graduate extends User {
    Integer age;
    Sex sex;

    public Graduate(String id, String login, String password,
                    String email, String fio, Role role, Integer age, Sex sex) {
        super(id, login, password, email, fio, role);

        this.age = age;
        this.sex = sex;
    }
}
