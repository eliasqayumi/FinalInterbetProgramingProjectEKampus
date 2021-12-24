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
public class SubjectController {

    private SubjectService subjectService;
    private LessonTakenService lessonTakenService;
    private StudentService studentService;
    private SubjectTeachService subjectTeachService;
    private TeacherService teacherService;
    private DepartmentService departmentService;
    private TeacherInfoService teacherInfoService;

    public SubjectController(SubjectService subjectService, LessonTakenService lessonTakenService,
                             TeacherService teacherService, TeacherInfoService teacherInfoService,
                             StudentService studentService, SubjectTeachService subjectTeachService,
                             DepartmentService departmentService) {
        this.subjectService = subjectService;
        this.lessonTakenService = lessonTakenService;
        this.studentService = studentService;
        this.subjectTeachService = subjectTeachService;
        this.teacherService = teacherService;
        this.teacherInfoService = teacherInfoService;
        this.departmentService = departmentService;
    }

    @RequestMapping("/subject")
    public String getAllSubject(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null && (user.getUserRole().equalsIgnoreCase("admin") || user.getUserRole().equalsIgnoreCase("student"))) {
        List<Subject> subjects = subjectService.finAllSubjects();
        model.addAttribute("subjects", subjects);
        if (user.getUserRole().equalsIgnoreCase("student")) {
            List<LessonTaken> lessonTakes = lessonTakenService
                    .findAllLessonTakenByStudentId(studentService.findStudentByStudentEmail(user.getUserEmail()).getStudentId());
            model.addAttribute("lessonTakes", lessonTakes);
            return "student/subject";
        }
        return "admin/panel/panelSubject";
        } else
            return "redirect:/";

    }


    @RequestMapping("/subject/add")
    public String addNewSubject(@ModelAttribute("subject") Subject subject, HttpServletRequest request) {
        if (getString(request)) return "redirect:/";
        long count = subjectService.finAllSubjects().size();
        String id = "SUB" + (++count);
        if (subject.getSubjectName() != null && subject.getSubjectCredit() != null) {
            Subject newSubject = new Subject(id, subject.getSubjectName(), subject.getSubjectCredit());
            subjectService.addSubject(newSubject);

            return "redirect:/subject";
        }
        return "admin/add/addNewSubject";
    }

    @RequestMapping("/subject/edit/{id}")
    public String editSubject(@PathVariable String id, @ModelAttribute("subject") Subject subject, HttpServletRequest request, Model model) {
        if (getString(request)) return "redirect:/";
        subject = subjectService.findSubjectById(id);
        model.addAttribute("subject", subject);
        return "admin/update/updateSubject";
    }

    @RequestMapping("/subject/update/{id}")
    public String updateSubject(@PathVariable String id, @ModelAttribute("subject") @Valid Subject subject, HttpServletRequest request) {
        if (getString(request)) return "redirect:/";
        Subject updatedSubject = subjectService.findSubjectById(id);
        if (subject.getId() != null && subject.getSubjectName() != null && subject.getSubjectCredit() != null) {
            updatedSubject.setId(id);
            updatedSubject.setSubjectName(subject.getSubjectName());
            updatedSubject.setSubjectCredit(subject.getSubjectCredit());
            subjectService.updateSubject(updatedSubject);
            return "redirect:/subject";
        }
        return "admin/update/updateSubject";
    }

    @RequestMapping("/subject/teachBy")
    public String subjectTeachBy(Model model, HttpServletRequest request) {

        List<SubjectTeach> subjectTeaches = subjectTeachService.finAllSubjectTeachs();
        if (subjectTeaches != null) {
            List<Subject> subjects = subjectService.finAllSubjects();
            List<Teacher> teachers = teacherService.finAllTeachers();
            List<Department> departments = departmentService.findAllDepartment();
            List<TeacherInfo> teacherInfos = teacherInfoService.finAllTeacherInfos();
            model.addAttribute("subjects", subjects);
            model.addAttribute("teachers", teachers);
            model.addAttribute("subjectTeaches", subjectTeaches);
            model.addAttribute("departments", departments);
            model.addAttribute("teacherInfos", teacherInfos);
        }
        return "admin/panel/panelSubjectTeachBy";
    }

    @RequestMapping("/subject/teachBy/add")
    public String subjectTeachByAdd(Model model, HttpServletRequest request) {
        List<Subject> subjects = subjectService.finAllSubjects();
        List<Teacher> teachers = teacherService.finAllTeachers();
        model.addAttribute("subjects", subjects);
        model.addAttribute("teachers", teachers);
        String subjectId = request.getParameter("subject");
        String teacherId = request.getParameter("teacher");
        String start = request.getParameter("startDate");
        String finish = request.getParameter("finishDate");
        LocalDate startDate = null;
        LocalDate finishDate = null;
        if (start != null && finish != null) {
            startDate = LocalDate.parse(start);
            finishDate = LocalDate.parse(finish);
        }
        long count = subjectTeachService.finAllSubjectTeachs().size();
        String lessonId = "TBY" + (++count);
        if (subjectId != null && teacherId != null && startDate != null && finishDate != null) {
            SubjectTeachId subjectTeachId = new SubjectTeachId();
            subjectTeachId.setId(lessonId);
            subjectTeachId.setTeacherId(teacherId);
            subjectTeachId.setSubjectId(subjectId);
            subjectTeachId.setStartDate(startDate);
            subjectTeachId.setFinishDate(finishDate);
            SubjectTeach subjectTeach = new SubjectTeach();
            subjectTeach.setId(subjectTeachId);
            subjectTeachService.addSubjectTeach(subjectTeach);
            return "redirect:/subject/teachBy";
        }

        return "admin/add/addNewSubjectTeachBy";
    }

    @RequestMapping("/teacher/subjects")
    public String teacherSubject(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getUserRole().equalsIgnoreCase("teacher"))
            return "redirect:/";
        Teacher teacher = teacherService.findTeacherByEmail(user.getUserEmail());
        List<SubjectTeach> subjectTeaches = subjectTeachService.findAllSubjectTeachByTeacherId(teacher.getId());
        List<Subject> subjects = subjectService.finAllSubjects();
        model.addAttribute("subjects", subjects);
        model.addAttribute("subjectTeaches", subjectTeaches);
        return "teacher/subject";
    }

    private boolean getString(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getUserRole().equalsIgnoreCase("admin"))
            return true;
        return false;
    }


}
