package ru.eltex.accountingsystem.documents;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.eltex.accountingsystem.enums.Sex;
import ru.eltex.accountingsystem.enums.Role;
import ru.eltex.accountingsystem.User;

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
