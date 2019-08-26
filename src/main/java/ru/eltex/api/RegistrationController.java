
package ru.eltex.api;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.eltex.accountsystem.service.UserRegistrationService;

@Controller
public class RegistrationController {
    private final UserRegistrationService userRegistrationService;
    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    public RegistrationController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @GetMapping(value = "/reg")
    public String registerPage() {
        return "registration";
    }

    @RequestMapping(value = "/reg12", method = RequestMethod.POST)
    @ResponseBody
    public String register(@RequestBody JsonNode jsonNode) {
        return userRegistrationService.register(jsonNode);
    }
}
