package ru.eltex.accountsystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;
import ru.eltex.accountsystem.enums.Role;

import java.util.List;
import java.util.Map;

/**
 * Класс представления роли пользователя
 * @author Arishenk
 * @version v2.0
 */
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "user_role")
public class UserRole implements UserDetails {
    /**
     * Поле идентификатора
     */
    @Id
    private String id;

    private List<Role> authorities;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;


    /**
     * Поле логина
     */
    private String username;

    /**
     * Поле пароля
     */
    private String password;

    private boolean enabled;
    /**
     * Поле роли
     */
    private Role userRole;

    /**
     * Поле идентификатора пользователя в БД учителя/студента
     */
    private String userId;

    public UserRole(List<Role> authorities, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, String username, String password, boolean enabled, String userId) {
        this.authorities = authorities;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.userId = userId;
    }

    public UserRole(String username, String password, Role userRole, String userId) {
        this.username = username;
        this.password = password;
        this.userRole = userRole;
        this.userId = userId;
    }

    public UserRole(Map<String, String> user) {
        this.userId = user.get("id");
        this.username = user.get("login");
        this.password = user.get("password");
        this.userRole = Role.valueOf(user.get("role"));
    }

}



