package ru.eltex.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.eltex.accountsystem.model.Group;
import ru.eltex.accountsystem.model.Subject;
import ru.eltex.accountsystem.model.Task;
import ru.eltex.accountsystem.model.users.Teacher;
import ru.eltex.accountsystem.service.GroupService;
import ru.eltex.accountsystem.service.StudentService;
import ru.eltex.accountsystem.service.TeacherService;

@Controller
public class TeacherController {
    private final TeacherService teacherService;
    private final GroupService groupService;
    private final StudentService studentService;

    @Autowired
    public TeacherController(TeacherService teacherService, GroupService groupService, StudentService studentService) {
        this.teacherService = teacherService;
        this.groupService = groupService;
        this.studentService = studentService;
    }

    // В методах, отдающих страницы, в URL адрессе не должно содержаться слэшей, это меняет работу Thymeleaf.
    // Страницы html НЕ менуются по верблюжьей нотации. Лучше использовать нижнее подчеркивание.

    @RequestMapping(value = "/teacher/{idTeacher}", method = RequestMethod.GET)
    public String getTeacher(@PathVariable("idTeacher") String idTeacher, Model modelTeacher) {
        modelTeacher.addAttribute("teacher", teacherService.getTeacher(idTeacher));
        return "teacher_main";
    }

    @RequestMapping(value = "/teacher/{idTeacher}/subjects", method = RequestMethod.GET)
    public String getTeacherSubjects(@PathVariable("idTeacher") String idTeacher, Model model) {
        model.addAttribute("teacher", teacherService.getTeacher(idTeacher));
        model.addAttribute("teacherSubjects", teacherService.getTeacherSubjects(idTeacher));
        return "teacher_subjects";
    }

//    @RequestMapping(value = "/teacher_{idTeacher}_subject_{idSubject}", method = RequestMethod.GET)
    @RequestMapping(value = "/teacher/{idTeacher}/subject/{idSubject}", method = RequestMethod.GET)
    public String getSubjectGroups(@PathVariable("idTeacher") String idTeacher,
                                   @PathVariable("idSubject") String idSubject,
                                   Model model) {
        model.addAttribute("teacher", teacherService.getTeacher(idTeacher));
        model.addAttribute("teacherSubject", teacherService.getTeacherSubjects(idTeacher));
        return "teacher_sbjct_grps";
    }

    @RequestMapping(value = "/teacher/{idTeacher}/subjects/add_subject", method = RequestMethod.GET)
    public String addSubject(Model model){
        return "teacher_add_subject";
    }

    @RequestMapping(value = "/teacher_{idTeacher}_groups", method = RequestMethod.GET)
    public String getTeacherGroups(@PathVariable("idTeacher") String idTeacher, Model modelGroup) {
        modelGroup.addAllAttributes(teacherService.getTeacherGroups(idTeacher));
        return "teacher_groups";
    }

    @RequestMapping(value = "/teacher_{idTeacher}_getStudentsFromGroup_{idGroup}", method = RequestMethod.GET)
    public String getStudentsFromGroup(@PathVariable("idTeacher") String id, @PathVariable("idGroup") String idGroup, Model modelStudents) {
        modelStudents.addAllAttributes(teacherService.getStudentsFromGroup(idGroup));
        return "teacher_students_from_group";
    }


    //REST METHODS
    @GetMapping(value = "/teacher/{id}/getInfo")
    @ResponseBody
    public Teacher getTeacherInfo(@PathVariable("id") String id) {
        return teacherService.getTeacher(id);
    }

    @RequestMapping(value = "/teacher/{id}/subjects/{idSubject}/addGroup", method = RequestMethod.POST)
    @ResponseBody
    public void addGroup(@PathVariable("idSubject") String idSubject, @RequestBody Group group) {
        groupService.addGroup(idSubject, group);
        //если group.students != null заполнение у студентов subjects
    }

    @RequestMapping(value = "/addStudent/{groupId}/{studentId}", method = RequestMethod.POST)
    @ResponseBody
    public void addStudentInGroup(@PathVariable("groupId") String groupId, @PathVariable("studentId") String studentId) {
        groupService.addStudent(groupId, studentId);
        studentService.addSubjectForStudent(studentId);
        //заполнение у студента subjects
    }

    @RequestMapping(value = "/addSubject", method = RequestMethod.POST)
    @ResponseBody
    public void addSubject(@RequestBody Subject subject) {
        teacherService.addSubject(subject);
    }

    @RequestMapping(value = "/addScores/{studentId}/{taskId}/{scores}/{status}", method = RequestMethod.POST)
    @ResponseBody
    public void addScores(@PathVariable("studentId") String studentId,
                          @PathVariable("taskId") String taskId,
                          @PathVariable("scores") Integer scores,
                          @PathVariable("status") String status) {
        teacherService.addScores(studentId, taskId, status, scores);
    }


    @RequestMapping(value = "teacher_{teacherId}_subjects_{subjectId}_addTask", method = RequestMethod.POST)
    public void addTask(@PathVariable("teacherId") String teacherId, @PathVariable("subjectId") String subjectId, @RequestBody Task task) {
        teacherService.addTask(teacherId, subjectId, task);
    }

    // нужна отдельная страничка (Работа для Маши=))
    @RequestMapping(value = "teacher_{teacherId}_subjects_{subjectId}_{groupId}_TasksResults", method = RequestMethod.GET)
    public String getTasksResults(@PathVariable("groupId") String groupId, Model model) {
        model.addAttribute("tasksResults", teacherService.getTasks(groupId)); // добавление всех результатов тестов конкретной группы
        return "teacher_tasks_results";
    }
}