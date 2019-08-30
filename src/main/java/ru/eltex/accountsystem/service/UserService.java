package ru.eltex.accountsystem.service;

import org.springframework.stereotype.Service;
import ru.eltex.accountsystem.model.UserRole;
import ru.eltex.accountsystem.repository.AllUserRepository;

@Service
public class UserService {
    private final AllUserRepository allUserRepository;

    public UserService(AllUserRepository allUserRepository) {
        this.allUserRepository = allUserRepository;
    }

    public UserRole getUserRole(String id) {
        return allUserRepository.findById(id).get();
    }
}
