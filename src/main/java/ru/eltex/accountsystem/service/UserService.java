package ru.eltex.accountsystem.service;

import com.google.common.collect.ImmutableList;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.eltex.accountsystem.enums.Role;
import ru.eltex.accountsystem.model.UserRole;
import ru.eltex.accountsystem.repository.AllUserRepository;

import javax.annotation.PostConstruct;

@Service
public class UserService implements UserDetailsService {
    private final AllUserRepository allUserRepository;
    @Autowired
    public UserService(AllUserRepository allUserRepository) {
        this.allUserRepository = allUserRepository;
    }

    public UserRole getUserRole(String username) {
        return allUserRepository.findByUsername(username);
    }
    @PostConstruct
    public void init(){
        if(allUserRepository.findByUsername("stuDent")==null){
            allUserRepository.save(new UserRole(ImmutableList.of(Role.USER),true,true,true,"stuDent","{noop}studentPassword",true,"18794433-3966-46a0-9099-7a60384d848a"));
        }
    }
    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {

        return allUserRepository.findByUsername(username);
    }
}
