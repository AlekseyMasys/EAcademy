package ru.eltex.accountingsystem.documents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.eltex.accountingsystem.Sex;

@AllArgsConstructor
@Getter
@Setter
@Document(collection = "graduates")
public class Graduate {
    Integer age;
    Sex sex;
}
