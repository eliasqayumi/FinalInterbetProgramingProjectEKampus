package com.example.controller;

import com.example.model.*;
import com.example.service.*;
import lombok.Builder;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class StudentController {
    private StudentService studentService;
    private DepartmentService departmentService;
    private UserService userService;
    private StudentInfoService studentInfoService;

    public StudentController(StudentService studentService, DepartmentService departmentService,
                             UserService userService, StudentInfoService studentInfoService) {
        this.studentService = studentService;
        this.departmentService = departmentService;
        this.userService = userService;
        this.studentInfoService = studentInfoService;
    }

    @RequestMapping("/student")
    public String getAllStudent(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || user.getUserRole().equalsIgnoreCase("student"))
            return "redirect:/";
        List<Student> students = studentService.findAllStudents();
        List<StudentInfo> studentInfos = studentInfoService.findAllStudentInfo();
        if (students != null)
            model.addAttribute("students", students);
        if (studentInfos != null)
            model.addAttribute("studentInfos", studentInfos);

        if (user.getUserRole().equalsIgnoreCase("teacher"))
            return "teacher/studentPanel";

        return "admin/panel/panelStudent";
    }


    @RequestMapping("/student/add")
    public String addNewStudent(@ModelAttribute("student") Student student, Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getUserRole().equalsIgnoreCase("admin"))
            return "redirect:/";
        List<Department> departments = departmentService.findAllDepartment();
        model.addAttribute("departments", departments);
        LocalDate enrolDate = null;
        if (request.getParameter("enrolDate") != null)
            enrolDate = LocalDate.parse(request.getParameter("enrolDate"));
        String departmentId = request.getParameter("department");

        long count = studentService.findAllStudents().size();
        String studentId = "STUD" + (++count);

        if (student.getStudentName() != null && student.getStudentSurname() != null && enrolDate != null
                && student.getStudentTcNo() != null && student.getStudentPhoneNo() != null && departmentId != null
                && student.getStudentEmail() != null && student.getStudentAddress() != null && student.getStudentImageURL() != null) {

            Student newStudent = new Student(studentId, student.getStudentName(), student.getStudentSurname(),
                    student.getStudentTcNo(), student.getStudentPhoneNo(), student.getStudentEmail(),
                    student.getStudentAddress(), student.getStudentImageURL());
            StudentInfo studentInfo = new StudentInfo();
            studentInfo.setDepartment(departmentService.findDepartmentById(departmentId));
            studentInfo.setId(studentId);
            studentInfo.setEnrolDate(enrolDate);

            User newUser = new User(studentId, newStudent.getStudentName() + newStudent.getStudentSurname(),
                    newStudent.getStudentTcNo(), "Student", newStudent.getStudentEmail());

            studentService.addStudent(newStudent);
            studentInfoService.addStudentInfo(studentInfo);
            userService.addUser(newUser);
            return "redirect:/student";
        }
        return "admin/add/addNewStudent";
    }

    @RequestMapping("/student/edit/{id}")
    public String editStudent(@PathVariable String id, @ModelAttribute("student") Student student, HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getUserRole().equalsIgnoreCase("admin"))
            return "redirect:/";
        List<Department> departments = departmentService.findAllDepartment();
        student = studentService.findStudentById(id);
        StudentInfo studentInfo = studentInfoService.findStudentInfoById(id);
        model.addAttribute("departments", departments);
        model.addAttribute("studentInfo", studentInfo);
        model.addAttribute("student", student);
        return "admin/update/updateStudent";
    }

    @RequestMapping("/student/update/{id}")
    public String updateStudent(@PathVariable String id, @ModelAttribute("student") @Valid Student student, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getUserRole().equalsIgnoreCase("admin"))
            return "redirect:/";
        Student updateStudent = studentService.findStudentById(id);
        StudentInfo studentInfo = studentInfoService.findStudentInfoById(id);
        LocalDate enrolDate = null;
        if (request.getParameter("enrolDate") != null)
            enrolDate = LocalDate.parse(request.getParameter("enrolDate"));
        String departmentId = request.getParameter("department");
        if (student.getStudentName() != null && student.getStudentSurname() != null && enrolDate != null
                && student.getStudentTcNo() != null && student.getStudentPhoneNo() != null && departmentId != null
                && student.getStudentEmail() != null && student.getStudentAddress() != null && student.getStudentImageURL() != null) {
            updateStudent.setStudentId(id);
            updateStudent.setStudentName(student.getStudentName());
            updateStudent.setStudentSurname(student.getStudentSurname());
            updateStudent.setStudentTcNo(student.getStudentTcNo());
            updateStudent.setStudentPhoneNo(student.getStudentPhoneNo());
            updateStudent.setStudentEmail(student.getStudentEmail());
            updateStudent.setStudentAddress(student.getStudentAddress());
            updateStudent.setStudentImageURL(student.getStudentImageURL());
            studentService.updateStudent(updateStudent);

            studentInfo.setId(id);
            studentInfo.setDepartment(departmentService.findDepartmentById(departmentId));
            studentInfo.setEnrolDate(enrolDate);
            studentInfoService.updateStudentInfo(studentInfo);

            return "redirect:/student";
        }
        return "admin/update/updateStudent";
    }

}
