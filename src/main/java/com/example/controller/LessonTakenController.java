package com.example.controller;

import com.example.model.*;
import com.example.service.*;
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
public class LessonTakenController {

    private LessonTakenService lessonTakenService;
    private StudentService studentService;
    private SubjectService subjectService;
    private DepartmentService departmentService;
    private StudentInfoService studentInfoService;

    public LessonTakenController(LessonTakenService lessonTakenService, DepartmentService departmentService,
                                 StudentService studentService, SubjectService subjectService, StudentInfoService studentInfoService) {
        this.lessonTakenService = lessonTakenService;
        this.studentService = studentService;
        this.subjectService = subjectService;
        this.departmentService = departmentService;
        this.studentInfoService = studentInfoService;
    }

    //  Lesson taken resource
    @RequestMapping("/lessonTaken")
    public String getAllLessonTaken(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getUserRole().equalsIgnoreCase("admin"))
            return "redirect:/";
        List<LessonTaken> lessonTakes = lessonTakenService.findAllLessonTaken();
        List<Student> students = studentService.findAllStudents();
        List<Subject> subjects = subjectService.finAllSubjects();
        List<Department> departments = departmentService.findAllDepartment();
        List<StudentInfo> studentInfos = studentInfoService.findAllStudentInfo();

        if (lessonTakes != null) {
            model.addAttribute("lessonTakes", lessonTakes);
            model.addAttribute("students", students);
            model.addAttribute("subjects", subjects);
            model.addAttribute("departments", departments);
            model.addAttribute("studentInfos", studentInfos);
        }
        return "admin/panel/panelLessonTaken";
    }

    @RequestMapping("/lessonTaken/add")
    public String addNewLessonTaken(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getUserRole().equalsIgnoreCase("admin"))
            return "redirect:/";
        List<Subject> subjects = subjectService.finAllSubjects();
        List<Student> students = studentService.findAllStudents();

        String subjectId = request.getParameter("subject");
        String studentId = request.getParameter("student");
        String start = request.getParameter("startDate");
        String finish = request.getParameter("finishDate");
        LocalDate startDate = null;
        LocalDate finishDate = null;
        if (start != null && finish != null) {
            startDate = LocalDate.parse(start);
            finishDate = LocalDate.parse(finish);
        }
        model.addAttribute("subjects", subjects);
        model.addAttribute("students", students);
        long count = lessonTakenService.findAllLessonTaken().size();
        String lessonId = "LESS" + (++count);
        if (subjectId != null && studentId != null && startDate != null && finishDate != null) {
            LessonTaken lessonTaken = new LessonTaken();
            lessonTaken.setId(lessonId);
            lessonTaken.setSubject(subjectService.findSubjectById(subjectId));
            lessonTaken.setStudent(studentService.findStudentById(studentId));
            lessonTaken.setStartDate(startDate);
            lessonTaken.setFinishDate(finishDate);
            lessonTakenService.addLessonTaken(lessonTaken);
            return "redirect:/lessonTaken";
        }
        return "admin/add/addNewLessonTaken";
    }

    @RequestMapping("/lessonTaken/edit/{id}")
    public String updateLessonTaken(@PathVariable String id, @ModelAttribute("lessonTaken") LessonTaken lessonTaken, HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getUserRole().equalsIgnoreCase("admin"))
            return "redirect:/";
        List<Student> students = studentService.findAllStudents();
        List<Subject> subjects = subjectService.finAllSubjects();
        lessonTaken = lessonTakenService.findLessonTakenById(id);
        model.addAttribute("lessonTaken", lessonTaken);
        model.addAttribute("students", students);
        model.addAttribute("subjects", subjects);
        return "admin/update/updateLessonTaken";
    }

    @RequestMapping("/lessonTaken/update/{id}")
    public String update(@PathVariable String id,  HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getUserRole().equalsIgnoreCase("admin"))
            return "redirect:/";

        String subjectId = request.getParameter("subject");
        String studentId = request.getParameter("student");
        String start = request.getParameter("startDate");
        String finish = request.getParameter("finishDate");
        LocalDate startDate = null;
        LocalDate finishDate = null;
        if (start != null && finish != null) {
            startDate = LocalDate.parse(start);
            finishDate = LocalDate.parse(finish);
        }
               if (subjectId != null && studentId != null && startDate != null && finishDate != null) {
            LessonTaken updateLessonTaken = lessonTakenService.findLessonTakenById(id);
            updateLessonTaken.setId(id);
            updateLessonTaken.setStudent(studentService.findStudentById(studentId));
            updateLessonTaken.setSubject(subjectService.findSubjectById(subjectId));
            updateLessonTaken.setStartDate(startDate);
            updateLessonTaken.setFinishDate(finishDate);
            lessonTakenService.updateLessonTaken(updateLessonTaken);
            return "redirect:/lessonTaken";
//
        }
        return "admin/update/updateLessonTaken";
    }

    @RequestMapping("/lessonTaken/delete/{id}")
    public String deleteLessonTaken(@PathVariable String id, HttpServletRequest request) {
        lessonTakenService.deleteLessonTakenId(id);
        return "redirect:/lessonTaken";
    }
}
