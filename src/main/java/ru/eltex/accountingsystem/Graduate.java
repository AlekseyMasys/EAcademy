package ru.eltex.accountingsystem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Getter
@Setter
@Document(collection = "graduates")
public class Graduate {
    Integer age;
    Sex sex;
}
