
package ru.eltex.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.eltex.accountsystem.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/users/{userLogin}")
    public String getUserPage(@PathVariable("userLogin") String userLogin, Model modelUser) {
        return userService.getUserPage(userLogin, modelUser);
    }


}
