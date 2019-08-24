
package ru.eltex.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.eltex.accountsystem.service.UserRegistrationService;

@RestController
public class RegistrationController {
    private final UserRegistrationService userRegistrationService;
    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
    private final ObjectMapper objectMapper;

    @Autowired
    public RegistrationController(UserRegistrationService userRegistrationService, ObjectMapper objectMapper) {
        this.userRegistrationService = userRegistrationService;
        this.objectMapper = objectMapper;
    }

    @PostMapping(value = "/registration")
    public String register(@RequestBody JsonNode jsonNode){
        return userRegistrationService.register(jsonNode);
    }
}
