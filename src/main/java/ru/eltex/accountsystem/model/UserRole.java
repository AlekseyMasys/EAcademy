package ru.eltex.accountsystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.eltex.accountsystem.enums.Role;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "user_role")
public class UserRole {
    @Id
    private String id;
    private String userLogin;
    private String userPassword;
    private Role userRole;
    private String userId;

    public UserRole(String userLogin, String userPassword, Role userRole, String userId) {
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.userRole = userRole;
        this.userId = userId;
    }

    public UserRole(Map<String, String> user) {
        this.userId = user.get("id");
        this.userLogin = user.get("login");
        this.userPassword = user.get("password");
        this.userRole = Role.valueOf(user.get("role"));
    }
}
