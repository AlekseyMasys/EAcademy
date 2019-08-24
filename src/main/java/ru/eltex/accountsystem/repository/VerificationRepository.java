package ru.eltex.accountsystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.eltex.accountsystem.model.UserVerificat;

public interface VerificationRepository extends MongoRepository<UserVerificat, String> {
    UserVerificat findByUserLoginAndPassword(String userLogin, String userPassword);
}
