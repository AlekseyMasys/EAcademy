package ru.eltex.accountsystem.service;

import org.springframework.stereotype.Service;
import ru.eltex.accountsystem.dao.UserRoleRepository;
import ru.eltex.accountsystem.model.UserRole;

@Service
public class UserService {
    private final UserRoleRepository userRoleRepository;

    public UserService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public UserRole getUserRole(String id) {
        return userRoleRepository.findById(id).get();
    }
}
