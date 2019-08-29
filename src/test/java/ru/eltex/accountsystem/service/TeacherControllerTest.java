package ru.eltex.accountsystem.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.eltex.accountsystem.enums.Role;
import ru.eltex.accountsystem.model.User;
import ru.eltex.accountsystem.model.users.Student;
import ru.eltex.accountsystem.model.users.Teacher;
import ru.eltex.accountsystem.repository.StudentRepository;
import ru.eltex.accountsystem.repository.TeacherRepository;
import ru.eltex.api.StudentController;
import ru.eltex.api.TeacherController;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TeacherControllerTest {

    @Autowired
    private TeacherController teacherController;

    @Test
    public void contextLoads() throws Exception {
        org.assertj.core.api.Assertions.assertThat(teacherController).isNotNull();
    }

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private MockMvc mockMvc;
    @Test
    public void getUser() throws Exception {
        Teacher teacher = new Teacher("login_test", "password_test", "email_test", "fio_test", Role.TEACHER, null);
        teacherRepository.save(teacher);
        Teacher teacher1 = teacherRepository.findByFio("fio_test");
        try {
            this.mockMvc.perform(get("http://localhost:8089/teacher/" + teacher1.getId())).andDo(print()).andExpect(status().isOk())
                    .andExpect(content().string(containsString("Лучшая система учета студентов"))).andExpect(content().string(containsString("Обратная связь")))
                    .andExpect(content().string(containsString("Ф.И.О.: fio_test"))).andExpect(content().string(containsString("Email: email_test")));
        }
        finally {
            teacherRepository.deleteById(teacher1.getId());
        }
    }




}

//    @Test
//    public void getUser() {
//        Student student1 = new Student("log", "pass", "ema", "fio", Role.STUDENT, null);
//        Student student2 = new Student("log2", "pass2", "ema2", "fio2", Role.STUDENT, null);
//        Student st1 = studentRepository.save(student1);
//        Student st2 = studentRepository.save(student2);
//        studentRepository.findAll();
//
//    }
