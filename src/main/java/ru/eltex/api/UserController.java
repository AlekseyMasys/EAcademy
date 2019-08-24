
package ru.eltex.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.eltex.accountsystem.model.User;
import ru.eltex.accountsystem.model.UserRole;
import ru.eltex.accountsystem.service.UserRegistrationService;

@Controller
public class UserController {

    private final UserRegistrationService userRegistrationService;

    @Autowired
    public UserController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @RequestMapping("/registration")
    public String register(@RequestBody User user) {
        return userRegistrationService.register(user);
    }


}
