package ru.eltex.accountingsystem.documents;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.eltex.accountingsystem.Sex;
import ru.eltex.accountingsystem.Status;
import ru.eltex.accountingsystem.User;

@Getter
@Setter
@Document(collection = "graduates")
public class Graduate extends User {
    Integer age;
    Sex sex;

    public Graduate(Integer id, String login, String password,
                    String email, String fio, Status status, Integer age, Sex sex) {
        super(id, login, password, email, fio, status);
        this.age = age;
        this.sex = sex;
    }
}
