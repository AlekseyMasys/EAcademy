package ru.eltex.accountsystem.service;

import ru.eltex.accountsystem.enums.Role;
import ru.eltex.accountsystem.model.User;
import ru.eltex.accountsystem.model.UserRole;
import ru.eltex.accountsystem.model.users.Admin;
import ru.eltex.accountsystem.model.users.Graduate;
import ru.eltex.accountsystem.model.users.Student;
import ru.eltex.accountsystem.model.users.Teacher;
import ru.eltex.accountsystem.repository.*;

import java.util.UUID;

public class UserRegistrationService {

    private final AllUserRepository allUserRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final AdminRepository adminRepository;
    private final GraduateRepository graduateRepository;

    public UserRegistrationService(AllUserRepository allUserRepository, StudentRepository studentRepository, TeacherRepository teacherRepository, AdminRepository adminRepository, GraduateRepository graduateRepository) {
        this.allUserRepository = allUserRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.adminRepository = adminRepository;
        this.graduateRepository = graduateRepository;
    }


    public String getUserPage(User user) {
        String userLogin = user.getLogin();
        String userPassword = user.getPassword();

        if(allUserRepository.findByUserLoginAndUserPassword(userLogin, userPassword) != null) {
            return "Ошибка, пользователь с таким логином и паролем уже существует.";
        }
        else {
            Role userRoleFromRegistration = user.getRole(); // выбранная роль при регистрации
            String userId = UUID.randomUUID().toString();
            String userEmail = user.getEmail();
            String userFio = user.getFio();

            switch (userRoleFromRegistration) {
                case GRADUATE: {
                    Graduate graduate = new Graduate(userId, userLogin, userPassword, userEmail, userFio, userRoleFromRegistration, null, null);
                    graduateRepository.save(graduate); // TODO: 24.08.2019
                    break;
                }

                case TEACHER: {
                    Teacher teacher = new Teacher(userId, userLogin, userPassword, userEmail, userFio, userRoleFromRegistration, null);
                    teacherRepository.save(teacher);
                    break;
                }

                case STUDENT: {
                    Student student = new Student(userId, userLogin, userPassword, userEmail, userFio, userRoleFromRegistration, null);
                    studentRepository.save(student);
                    break;
                }
                case ADMIN: {
                    Admin admin = new Admin(userId, userLogin, userPassword, userEmail, userFio, userRoleFromRegistration);
                    adminRepository.save(admin);
                }
            }
            UserRole userRole = new UserRole(userLogin, userPassword, userRoleFromRegistration, userId);
            allUserRepository.save(userRole);
            return "Регистрация прошла успешно.";
        }
    }
}