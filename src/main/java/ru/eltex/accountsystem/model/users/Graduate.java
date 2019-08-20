package ru.eltex.accountsystem.model.users;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.eltex.accountsystem.enums.Sex;
import ru.eltex.accountsystem.enums.Role;
import ru.eltex.accountsystem.model.User;

@Getter
@Setter
@Document(collection = "graduates")
public class Graduate extends User {
    Integer age;
    Sex sex;

    public Graduate(String login, String password,
                    String email, String fio, Role role, Integer age, Sex sex) {
        super(login, password, email, fio, role);

        this.age = age;
        this.sex = sex;
    }
}
