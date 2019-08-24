package ru.eltex.accountsystem.service;

import org.springframework.ui.Model;
import ru.eltex.accountsystem.model.User;
import ru.eltex.accountsystem.model.UserVerificat;
import ru.eltex.accountsystem.repository.*;

public class UserService {

    private final VerificationRepository verificationRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final AdminRepository adminRepository;
    private final GraduateRepository graduateRepository;

    public UserService(VerificationRepository verificationRepository, StudentRepository studentRepository, TeacherRepository teacherRepository, AdminRepository adminRepository, GraduateRepository graduateRepository) {
        this.verificationRepository = verificationRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.adminRepository = adminRepository;
        this.graduateRepository = graduateRepository;
    }


    public String getUserPage(String userLogin, Model modele) {
        UserVerificat userVerificat = verificationRepository.findByUserLoginAndPassword(userLogin);
        String resultPage = null;
        if (userVerificat == null) {
            resultPage = "not_found"; //incorrect login or password
        }
        else {
            String userId = userVerificat.getUserId();
            switch (userVerificat.getUserRole()) {
                case ADMIN: {
                    modele.addAttribute("user", adminRepository.findById(userId).get());
                    resultPage = "admin_page";
                }
                case STUDENT: {
                    modele.addAttribute("user", studentRepository.findById(userId).get());
                    resultPage =  "student_page";
                }

                case TEACHER: {
                    modele.addAttribute("user", teacherRepository.findTeacherById(userId));
                    resultPage =  "teacher_page";
                }
                case GRADUATE:{
                    modele.addAttribute("user", graduateRepository.findById(userId).get());
                    resultPage =  "graduate_page";
                }

            }
        }
        return resultPage;
    }
}