package ru.eltex.api;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.eltex.accountsystem.model.Group;
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
    private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);

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
        logger.info("start getTeacher()");
        logger.debug("request id = " + idTeacher);
        logger.debug("response student_main");
        modelTeacher.addAttribute("teacher", teacherService.getTeacher(idTeacher));
        return "teacher_main";
    }

    @RequestMapping(value = "/teacher/{idTeacher}/subjects", method = RequestMethod.GET)
    public String getTeacherSubjects(@PathVariable("idTeacher") String idTeacher, Model model) {
        logger.info("start getTeacherSubjects()");
        logger.debug("request teacherId = " + idTeacher);
        logger.debug("response teacher_subjects");
        model.addAttribute("teacher", teacherService.getTeacher(idTeacher));
        model.addAttribute("teacherSubjects", teacherService.getTeacherSubjects(idTeacher));
        return "teacher_subjects";
    }

//    @RequestMapping(value = "/teacher_{idTeacher}_subject_{idSubject}", method = RequestMethod.GET)
    @RequestMapping(value = "/teacher/{idTeacher}/subjects/{idSubject}", method = RequestMethod.GET)
    public String getSubjectGroups(@PathVariable("idTeacher") String idTeacher,
                                   @PathVariable("idSubject") String idSubject,
                                   Model model) {
        logger.info("start getSubjectGroups()");
        logger.debug("request teacherId = " + idTeacher);
        logger.debug("response teacher_sbjct_grps");
        model.addAttribute("teacher", teacherService.getTeacher(idTeacher));
        model.addAttribute("teacherSubject", teacherService.getTeacherSubjects(idTeacher));
        return "teacher_sbjct_grps";
    }

//    @RequestMapping(value = "/teacher/{idTeacher}/subjects/add_subject", method = RequestMethod.GET)
//    public String addSubject(Model model){
//        return "teacher_add_subject";
//    }

    @RequestMapping(value = "/teacher_{idTeacher}_groups", method = RequestMethod.GET)
    public String getTeacherGroups(@PathVariable("idTeacher") String idTeacher, Model modelGroup) {
        logger.info("start getTeacherGroups()");
        logger.debug("request id = " + id);
        logger.debug("response teacher_groups");
        modelGroup.addAllAttributes(teacherService.getTeacherGroups(idTeacher));
        return "teacher_groups";
    }

    @RequestMapping(value = "/teacher_{idTeacher}_getStudentsFromGroup_{idGroup}", method = RequestMethod.GET)
    public String getStudentsFromGroup(@PathVariable("idTeacher") String id, @PathVariable("idGroup") String idGroup, Model modelStudents) {
        logger.info("start getStudentsFromGroup()");
        logger.debug("request id = " + id);
        logger.debug("response teacher_students_from_group");
        modelStudents.addAllAttributes(teacherService.getStudentsFromGroup(idGroup));
        return "teacher_students_from_group";
    }


    //REST METHODS
    @GetMapping(value = "/teacher/{id}/getInfo")
    @ResponseBody
    public Teacher getTeacherInfo(@PathVariable("id") String id) {
        logger.info("start getTeacherInfo()");
        logger.debug("request id = " + id);
        Teacher teacher = teacherService.getTeacher(id);
        logger.debug("response " + teacher);
        return teacher;
    }

    @RequestMapping(value = "/teacher/{id}/subjects/{idSubject}/add_group", method = RequestMethod.POST)
    @ResponseBody
    public String addGroup(@PathVariable("idSubject") String idSubject, @RequestBody Group group) {
        logger.info("start addGroup()");
        logger.debug("request idSubject = " + idSubject + "Group " + group.toString());
        groupService.addGroup(idSubject, group);
        //если group.students != null заполнение у студентов subjects
        return "cool";
    }

    @RequestMapping(value = "/addStudent/{groupId}/{studentId}", method = RequestMethod.POST)
    @ResponseBody
    public void addStudentInGroup(@PathVariable("groupId") String groupId, @PathVariable("studentId") String studentId) {
        logger.info("start addStudentInGroup()");
        logger.debug("request groupId = " + groupId + "studentId = " + studentId);
        groupService.addStudent(groupId, studentId);
        studentService.addSubjectForStudent(studentId);
        //заполнение у студента subjects
    }

    @RequestMapping(value = "/teacher/{teacherId}/subjects/add_subject", method = RequestMethod.POST)
    @ResponseBody
    public String addSubject(@PathVariable("teacherId") String teacherId,@RequestBody JsonNode subject) {
        logger.info("start addSubject()");
        logger.debug("request subject = " + subject.toString());
        return   teacherService.addSubject(teacherId,subject);
    }

    @RequestMapping(value = "/addScores/{studentId}/{taskId}/{scores}/{status}", method = RequestMethod.POST)
    @ResponseBody
    public void addScores(@PathVariable("studentId") String studentId,
                          @PathVariable("taskId") String taskId,
                          @PathVariable("scores") Integer scores,
                          @PathVariable("status") String status) {
        logger.info("start addScores()");
        logger.debug("request idStudent = " + studentId + "taskId = " + taskId
                + " scores = " + scores + " status = " + status);
        teacherService.addScores(studentId, taskId, status, scores);
    }


    @RequestMapping(value = "teacher_{teacherId}_subjects_{subjectId}_addTask", method = RequestMethod.POST)
    public void addTask(@PathVariable("teacherId") String teacherId, @PathVariable("subjectId") String subjectId, @
            RequestBody Task task) {
        logger.info("start addTask()");
        logger.debug("request teacherId = " + teacherId + " subjectId = " + subjectId);
        teacherService.addTask(teacherId, subjectId, task);
    }

    // нужна отдельная страничка (Работа для Маши=))
    @RequestMapping(value = "teacher_{teacherId}_subjects_{subjectId}_{groupId}_TasksResults", method = RequestMethod.GET)
    public String getTasksResults(@PathVariable("groupId") String groupId, Model model) {
        logger.info("start getTasksResults()");
        logger.debug("request idGroup = " + groupId);
        logger.debug("response teacher_tasks_results");
        model.addAttribute("tasksResults", teacherService.getTasks(groupId)); // добавление всех результатов тестов конкретной группы
        return "teacher_tasks_results";
    }
}