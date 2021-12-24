package com.example.controller;

import com.example.model.Department;
import com.example.model.Teacher;
import com.example.model.TeacherInfo;
import com.example.model.User;
import com.example.service.DepartmentService;
import com.example.service.TeacherInfoService;
import com.example.service.TeacherService;
import com.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
public class TeacherController {
    private TeacherService teacherService;
    private DepartmentService departmentService;
    private TeacherInfoService teacherInfoService;
    private UserService userService;

    public TeacherController(TeacherService teacherService, DepartmentService departmentService,
                             TeacherInfoService teacherInfoService, UserService userService) {
        this.teacherService = teacherService;
        this.departmentService = departmentService;
        this.teacherInfoService = teacherInfoService;
        this.userService = userService;
    }

    @RequestMapping("/teacher")
    public String teacherHome(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getUserRole().equalsIgnoreCase("admin"))
            return "redirect:/";
        List<Teacher> allTeachers = teacherService.finAllTeachers();
        List<TeacherInfo> teacherInfos = teacherInfoService.finAllTeacherInfos();
        if (allTeachers != null)
            model.addAttribute("allTeachers", allTeachers);
        if(teacherInfos!=null)
            model.addAttribute("teacherInfos",teacherInfos);
        return "admin/panel/panelTeacher";
    }

    @RequestMapping("/teacher/add")
    public String addNewTeacher(@ModelAttribute("teacher") @Valid Teacher teacher, Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getUserRole().equalsIgnoreCase("admin"))
            return "redirect:/";
        List<Department> departments = departmentService.findAllDepartment();
        model.addAttribute("departments", departments);
        LocalDate enrolDate = null;
        if (request.getParameter("enrolDate") != null)
            enrolDate = LocalDate.parse(request.getParameter("enrolDate"));
        String departmentId = request.getParameter("department");
        long count = teacherService.finAllTeachers().size();
        String teacherId = "TEACH" + (++count);
        if (teacher.getTeacherName() != null && teacher.getTeacherSurname() != null && enrolDate != null &&
                teacher.getTeacherTcno() != null && teacher.getTeacherPhone() != null && departmentId != null &&
                teacher.getTeacherEmail() != null && teacher.getTeacherAddress() != null) {
            Teacher newTeacher = new Teacher(teacherId, teacher.getTeacherName(), teacher.getTeacherSurname(),
                    teacher.getTeacherTcno(), teacher.getTeacherPhone(), teacher.getTeacherEmail(), teacher.getTeacherAddress());
            TeacherInfo teacherInfo = new TeacherInfo();
            teacherInfo.setId(teacherId);
            teacherInfo.setDepartment(departmentService.findDepartmentById(departmentId));
            teacherInfo.setInroleDate(enrolDate);
            User newUser = new User(teacherId, newTeacher.getTeacherName() + newTeacher.getTeacherSurname(),
                    newTeacher.getTeacherTcno(), "Teacher", teacher.getTeacherEmail());
            teacherService.addTeacher(newTeacher);
            teacherInfoService.addTeacherInfo(teacherInfo);
            userService.addUser(newUser);
            return "redirect:/teacher";
        }
        return "admin/add/addNewTeacher";
    }

    @RequestMapping("/teacher/edit/{id}")
    public String editTeacher(@PathVariable String id, @ModelAttribute("teacher") Teacher teacher, HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getUserRole().equalsIgnoreCase("admin"))
            return "redirect:/";
        TeacherInfo teacherInfo = teacherInfoService.findTeacherInfoById(id);
        List<Department> departments = departmentService.findAllDepartment();
        teacher = teacherService.findTeacherById(id);
        model.addAttribute("teacherInfo", teacherInfo);
        model.addAttribute("departments", departments);
        model.addAttribute("teacher", teacher);
        return "admin/update/updateTeacher";
    }

    @RequestMapping("/teacher/update/{id}")
    public String updateTeacher(@PathVariable String id, @ModelAttribute("teacher") @Valid Teacher teacher, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getUserRole().equalsIgnoreCase("admin"))
            return "redirect:/";
        Teacher updateTeacher = teacherService.findTeacherById(id);
        TeacherInfo teacherInfo = teacherInfoService.findTeacherInfoById(teacher.getId());
        LocalDate enrolDate = null;
        if (request.getParameter("enrolDate") != null)
            enrolDate = LocalDate.parse(request.getParameter("enrolDate"));
        String departmentId = request.getParameter("department");
        if (id != null && teacher.getTeacherName() != null && teacher.getTeacherSurname() != null && enrolDate != null
                && teacher.getTeacherTcno() != null && teacher.getTeacherPhone() != null && departmentId != null
                && teacher.getTeacherEmail() != null && teacher.getTeacherAddress() != null) {
            System.out.println(departmentId);
            updateTeacher.setId(id);
            updateTeacher.setTeacherName(teacher.getTeacherName());
            updateTeacher.setTeacherSurname(teacher.getTeacherSurname());
            updateTeacher.setTeacherTcno(teacher.getTeacherTcno());
            updateTeacher.setTeacherPhone(teacher.getTeacherPhone());
            updateTeacher.setTeacherEmail(teacher.getTeacherEmail());
            updateTeacher.setTeacherAddress(teacher.getTeacherAddress());
            teacherService.updateTeacher(teacher);

            teacherInfo.setId(id);
            teacherInfo.setDepartment(departmentService.findDepartmentById(departmentId));
            teacherInfo.setInroleDate(enrolDate);
            teacherInfoService.updateTeacherInfo(teacherInfo);

            return "redirect:/teacher";
        }
        return "admin/update/updateTeacher";
    }


}
