package com.example.controller;

import com.example.model.*;
import com.example.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
public class ApplicationController {
    private DepartmentService departmentService;
    private LessonTakenService lessonTakenService;
    private StudentService studentService;
    private SubjectService subjectService;
    private StudentInfoService studentInfoService;
    private UserService userService;
    private TeacherService teacherService;
    private TeacherInfoService teacherInfoService;
    private SubjectTeachService subjectTeachService;

    public ApplicationController(DepartmentService departmentService, LessonTakenService lessonTakenService, StudentService studentService, SubjectService subjectService, StudentInfoService studentInfoService, UserService userService, TeacherService teacherService, TeacherInfoService teacherInfoService, SubjectTeachService subjectTeachService) {
        this.departmentService = departmentService;
        this.lessonTakenService = lessonTakenService;
        this.studentService = studentService;
        this.subjectService = subjectService;
        this.studentInfoService = studentInfoService;
        this.userService = userService;
        this.teacherService = teacherService;
        this.teacherInfoService = teacherInfoService;
        this.subjectTeachService = subjectTeachService;
    }

    @RequestMapping("/")
    public String home(Model model, HttpServletRequest request) {
        request.getSession().invalidate();
        String username = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies)
                if (cookie.getName().equals("user"))
                    username = cookie.getValue();
        model.addAttribute("username", username);
        return "index";
    }

    @RequestMapping("/login")
    public String login(@ModelAttribute("user") User user, Model model, HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            user = userService.findUserByUserEmail(username);
            Cookie[] cookies = request.getCookies();
            if (cookies != null)
                for (Cookie cookie : cookies)
                    if (!cookie.getName().equals("user")) {
                        Cookie newCookie = new Cookie("user", username);
                        newCookie.setMaxAge(60 * 60 * 24);
                        response.addCookie(newCookie);
                    }

            if (user.getUserPassword().equals(password)) {
                request.getSession().invalidate();
                HttpSession newSession = request.getSession();
                newSession.setMaxInactiveInterval(3600);
                String encode = response.encodeURL(request.getContextPath());
                model.addAttribute("user", user);
                newSession.setAttribute("user", user);
                return "redirect:" + encode + user.getUserRole().toLowerCase() + "s";
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage()
            );
        }
        return "redirect:/";
    }

    @RequestMapping("/admins")
    public String adminPanel(@ModelAttribute("user") User user, HttpServletRequest request) {
//        User user1 = (User) request.getSession().getAttribute("user");
//        if (user1 == null || !user1.getUserRole().equalsIgnoreCase("admin"))
//            return "redirect:/";
        return "admin/add/admin";
    }

    @RequestMapping("/teachers")
    public String teacherPanel(@ModelAttribute("user") User user, Model model, HttpServletRequest request) {
//        User user1 = (User) request.getSession().getAttribute("user");
//        if (user1 == null || !user1.getUserRole().equalsIgnoreCase("teacher"))
//            return "redirect:/";
        return "teacher/teacher";
    }

    @RequestMapping("/students")
    public String studentPanel(@ModelAttribute("user") User user, HttpServletRequest request) {
//        User user1 = (User) request.getSession().getAttribute("user");
//        if (user1 == null || !user1.getUserRole().equalsIgnoreCase("student"))
//            return "redirect:/";
        return "student/student";
    }

    //  Lesson taken resource
    @RequestMapping("/programs")
    public String getAllLessonTaken(HttpServletRequest request, Model model) {
//        User user = (User) request.getSession().getAttribute("user");
//        if (user == null || !user.getUserRole().equalsIgnoreCase("admin"))
//            return "redirect:/";
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
        return "/admin/panel/panelPrograms";
    }

    @RequestMapping("/programs/add")
    public String addNewLessonTaken(Model model, HttpServletRequest request) {
//        User user = (User) request.getSession().getAttribute("user");
//        if (user == null || !user.getUserRole().equalsIgnoreCase("admin"))
//            return "redirect:/";
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
            return "redirect:/programs";
        }
        return "admin/add/addNewPrograms";
    }

    @RequestMapping("/programs/edit/{id}")
    public String updateLessonTaken(@PathVariable String id, @ModelAttribute("lessonTaken") LessonTaken lessonTaken, HttpServletRequest request, Model model) {
//        User user = (User) request.getSession().getAttribute("user");
//        if (user == null || !user.getUserRole().equalsIgnoreCase("admin"))
//            return "redirect:/";
        List<Student> students = studentService.findAllStudents();
        List<Subject> subjects = subjectService.finAllSubjects();
        lessonTaken = lessonTakenService.findLessonTakenById(id);
        model.addAttribute("programs", lessonTaken);
        model.addAttribute("students", students);
        model.addAttribute("subjects", subjects);
        return "updatePrograms";
    }

    @RequestMapping("/programs/update/{id}")
    public String update(@PathVariable String id, HttpServletRequest request) {
//        User user = (User) request.getSession().getAttribute("user");
//        if (user == null || !user.getUserRole().equalsIgnoreCase("admin"))
//            return "redirect:/";

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
            return "redirect:/programs";
//
        }
        return "updatePrograms";
    }

    @RequestMapping("/programs/delete/{id}")
    public String deleteLessonTaken(@PathVariable String id, HttpServletRequest request) {
        lessonTakenService.deleteLessonTakenId(id);
        return "redirect:/programs";
    }

    //    Department resource
    @RequestMapping("/department")
    public String getAllDepartment(HttpServletRequest request, Model model) {
//        User user = (User) request.getSession().getAttribute("user");
//        if (user == null || !user.getUserRole().equalsIgnoreCase("admin"))
//            return "redirect:/";
        List<Department> departments = departmentService.findAllDepartment();
        model.addAttribute("departments", departments);
        return "admin/panel/panelDepartment";
    }

    @RequestMapping("/student/subject")
    public String studentSubject(Model model) {
        List<Subject> subjects = subjectService.finAllSubjects();
        model.addAttribute("subjects", subjects);
        return "student/subject";
    }

    @RequestMapping("/department/delete/{id}")
    public String deleteDepartment(@PathVariable String id) {

        try {
            departmentService.deleteDepartment(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/department";
    }

    @RequestMapping("/student/score")
    public String scores() {
        return "student/scores";
    }

    @RequestMapping("/department/add")
    public String addNewDepartment(@ModelAttribute("department") Department department, HttpServletRequest request) {
//        User user = (User) request.getSession().getAttribute("user");
//        if (user == null || !user.getUserRole().equalsIgnoreCase("admin"))
//            return "redirect:/";

        long count = departmentService.findAllDepartment().size();
        String id = "CUM" + (++count);
        if (department.getDepartmentName() != null) {
            Department newDepartment = new Department(id, department.getDepartmentName());
            System.out.println(newDepartment.toString());
            departmentService.addDepartment(newDepartment);
            return "redirect:/department";
        }
        return "admin/add/addNewDepartment";
    }

    @RequestMapping("/department/edit/{id}")
    public String updateDepartment(@PathVariable String id, HttpServletRequest request, Model model) {
//        User user = (User) request.getSession().getAttribute("user");
//        if (user == null || !user.getUserRole().equalsIgnoreCase("admin"))
//            return "redirect:/";
        Department department = departmentService.findDepartmentById(id);
        model.addAttribute("department", department);
        return "admin/update/updateDepartment";
    }

    @RequestMapping("/department/update/{id}")
    public String update(@PathVariable String id, @ModelAttribute("department") @Valid Department department, HttpServletRequest request) {
//        User user = (User) request.getSession().getAttribute("user");
//        if (user == null || !user.getUserRole().equalsIgnoreCase("admin"))
//            return "redirect:/";
        Department updateDepartment = departmentService.findDepartmentById(id);
        if (department.getDepartmentName() != null) {
            updateDepartment.setId(id);
            updateDepartment.setDepartmentName(department.getDepartmentName());
            departmentService.updateDepartment(updateDepartment);
            return "redirect:/department";
        }
        return "admin/update/updateDepartment";
    }

    @RequestMapping("/student")
    public String getAllStudent(HttpServletRequest request, Model model) {
//        User user = (User) request.getSession().getAttribute("user");
//        if (user == null || user.getUserRole().equalsIgnoreCase("student"))
//            return "redirect:/";
        List<Student> students = studentService.findAllStudents();
        List<StudentInfo> studentInfos = studentInfoService.findAllStudentInfo();
        model.addAttribute("students", students);
        model.addAttribute("studentInfos", studentInfos);
        return "admin/panel/panelStudent";
    }


    @RequestMapping("/student/add")
    public String addNewStudent(@ModelAttribute("student") Student student, Model model, HttpServletRequest request, HttpServletResponse response) {
//        User user = (User) request.getSession().getAttribute("user");
//        if (user == null || !user.getUserRole().equalsIgnoreCase("admin"))
//            return "redirect:/";
        String cookId=null, cookName=null, cookSurname=null, cookTc=null, cookPhone=null, cookEmail=null, cookAddress=null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("id"))
                    cookId = cookie.getValue();
                if (cookie.getName().equals("name"))
                    cookName = cookie.getValue();
                if (cookie.getName().equals("surname"))
                    cookSurname = cookie.getValue();
                if (cookie.getName().equals("tc"))
                    cookTc = cookie.getValue();
                if (cookie.getName().equals("phone"))
                    cookPhone = cookie.getValue();
                if (cookie.getName().equals("email"))
                    cookEmail = cookie.getValue();
                if (cookie.getName().equals("address"))
                    cookAddress = cookie.getValue();
            }
        if(cookId!=null &&cookName!=null&& cookSurname!=null&&cookTc!=null&&cookPhone!=null && cookEmail!=null && cookAddress!=null){
            Student student1=new Student(cookId,cookName,cookSurname,cookTc,cookPhone,cookEmail,cookAddress,"not Available");
            model.addAttribute("student",student1);
        }

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
                && student.getStudentEmail() != null && student.getStudentAddress() != null) {

            Student newStudent = new Student(studentId, student.getStudentName(), student.getStudentSurname(), student.getStudentTcNo(), student.getStudentPhoneNo(), student.getStudentEmail(),
                    student.getStudentAddress(), "notAvailable");

            if (cookies != null)
                for (Cookie cookie : cookies) {
                    if (!cookie.getName().equals("id")) {
                        Cookie newCookie = new Cookie("id", studentId);
                        newCookie.setMaxAge(60 * 60);
                        response.addCookie(newCookie);
                    }
                    if (!cookie.getName().equals("name")) {
                        Cookie newCookie = new Cookie("name", student.getStudentName());
                        newCookie.setMaxAge(60 * 60);
                        response.addCookie(newCookie);
                    }

                    if (!cookie.getName().equals("surname")) {
                        Cookie newCookie = new Cookie("surname", student.getStudentSurname());
                        newCookie.setMaxAge(60 * 60);
                        response.addCookie(newCookie);
                    }
                    if (!cookie.getName().equals("tc")) {
                        Cookie newCookie = new Cookie("tc", student.getStudentTcNo());
                        newCookie.setMaxAge(60 * 60);
                        response.addCookie(newCookie);
                    }
                    if (!cookie.getName().equals("phone")) {
                        Cookie newCookie = new Cookie("phone", student.getStudentPhoneNo());
                        newCookie.setMaxAge(60 * 60);
                        response.addCookie(newCookie);
                    }
                    if (!cookie.getName().equals("email")) {
                        Cookie newCookie = new Cookie("email", student.getStudentEmail());
                        newCookie.setMaxAge(60 * 60);
                        response.addCookie(newCookie);
                    }
                    if (!cookie.getName().equals("address")) {
                        Cookie newCookie = new Cookie("address", student.getStudentAddress());
                        newCookie.setMaxAge(60 * 60);
                        response.addCookie(newCookie);
                    }
                }
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
//        User user = (User) request.getSession().getAttribute("user");
//        if (user == null || !user.getUserRole().equalsIgnoreCase("admin"))
//            return "redirect:/";
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
//        User user = (User) request.getSession().getAttribute("user");
//        if (user == null || !user.getUserRole().equalsIgnoreCase("admin"))
//            return "redirect:/";
        String departmentId = request.getParameter("department");
        Student updateStudent = studentService.findStudentById(id);
        StudentInfo studentInfo = studentInfoService.findStudentInfoById(id);
        LocalDate enrolDate = null;
        if (request.getParameter("enrolDate") != null)
            enrolDate = LocalDate.parse(request.getParameter("enrolDate"));
        System.out.println("dep" + departmentId + " Date " + enrolDate + " name " + student.getStudentName() + " surname " + student.getStudentSurname());
        if (student.getStudentName() != null && student.getStudentSurname() != null && enrolDate != null
                && student.getStudentTcNo() != null && student.getStudentPhoneNo() != null && departmentId != null
                && student.getStudentEmail() != null && student.getStudentAddress() != null) {
            System.out.println("information please");
            updateStudent.setStudentId(id);
            updateStudent.setStudentName(student.getStudentName());
            updateStudent.setStudentSurname(student.getStudentSurname());
            updateStudent.setStudentTcNo(student.getStudentTcNo());
            updateStudent.setStudentPhoneNo(student.getStudentPhoneNo());
            updateStudent.setStudentEmail(student.getStudentEmail());
            updateStudent.setStudentAddress(student.getStudentAddress());
            updateStudent.setStudentImageURL("notAvailable");
            studentService.updateStudent(updateStudent);

            studentInfo.setId(id);
            studentInfo.setDepartment(departmentService.findDepartmentById(departmentId));
            studentInfo.setEnrolDate(enrolDate);
            studentInfoService.updateStudentInfo(studentInfo);

            return "redirect:/student";
        } else {

        }
        return "admin/update/updateStudent";
    }

    @RequestMapping("/teacher")
    public String teacherHome(Model model, HttpServletRequest request) {
//        User user = (User) request.getSession().getAttribute("user");
//        if (user == null || !user.getUserRole().equalsIgnoreCase("admin"))
//            return "redirect:/";
        List<Teacher> allTeachers = teacherService.finAllTeachers();
        List<TeacherInfo> teacherInfos = teacherInfoService.finAllTeacherInfos();
        model.addAttribute("allTeachers", allTeachers);
        model.addAttribute("teacherInfos", teacherInfos);
        return "admin/panel/panelTeacher";
    }


    @RequestMapping("/teacher/add")
    public String addNewTeacher(@ModelAttribute("teacher") @Valid Teacher teacher, Model model, HttpServletRequest request) {
//        User user = (User) request.getSession().getAttribute("user");
//        if (user == null || !user.getUserRole().equalsIgnoreCase("admin"))
//            return "redirect:/";
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

    @RequestMapping("/student/delete/{id}")
    public String deleteTeacher(@PathVariable String id) {
        try {
            studentService.deleteStudent(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/student";
    }

    @RequestMapping("/teacher/edit/{id}")
    public String editTeacher(@PathVariable String id, @ModelAttribute("teacher") Teacher teacher, HttpServletRequest request, Model model) {
//        User user = (User) request.getSession().getAttribute("user");
//        if (user == null || !user.getUserRole().equalsIgnoreCase("admin"))
//            return "redirect:/";
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
//        User user = (User) request.getSession().getAttribute("user");
//        if (user == null || !user.getUserRole().equalsIgnoreCase("admin"))
//            return "redirect:/";
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


    @RequestMapping("/subject")
    public String getAllSubject(HttpServletRequest request, Model model) {
//        User user = (User) request.getSession().getAttribute("user");
//        if (user != null && (user.getUserRole().equalsIgnoreCase("admin") || user.getUserRole().equalsIgnoreCase("student"))) {
        List<Subject> subjects = subjectService.finAllSubjects();
        model.addAttribute("subjects", subjects);
//            if (user.getUserRole().equalsIgnoreCase("student")) {
//                List<LessonTaken> lessonTakes = lessonTakenService
//                        .findAllLessonTakenByStudentId(studentService.findStudentByStudentEmail(user.getUserEmail()).getStudentId());
//                model.addAttribute("lessonTakes", lessonTakes);
//                return "student/subject";
//            }
        return "admin/panel/panelSubject";
//        } else
//            return "redirect:/";
//
    }


    @RequestMapping("/subject/add")
    public String addNewSubject(@ModelAttribute("subject") Subject subject, HttpServletRequest request) {
//        User user1 = (User) request.getSession().getAttribute("user");
//        if (user1 == null || !user1.getUserRole().equalsIgnoreCase("admin"))
//            return "redirect:/";
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
//        User user = (User) request.getSession().getAttribute("user");
//        if (user == null || !user.getUserRole().equalsIgnoreCase("admin"))
//            return "redirect:/";
        subject = subjectService.findSubjectById(id);
        model.addAttribute("subject", subject);
        return "admin/update/updateSubject";
    }

    @RequestMapping("/subject/update/{id}")
    public String updateSubject(@PathVariable String id, @ModelAttribute("subject") @Valid Subject subject, HttpServletRequest request) {
//        User user = (User) request.getSession().getAttribute("user");
//        if (user == null || !user.getUserRole().equalsIgnoreCase("admin"))
//            return "redirect:/";
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

    @RequestMapping("/subject/delete/{id}")
    public String deleteSubject(@PathVariable String id) {
        try {
            subjectService.deleteSubject(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/subject";
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
//        User user = (User) request.getSession().getAttribute("user");
//        if (user == null || !user.getUserRole().equalsIgnoreCase("teacher"))
//            return "redirect:/";
        List<SubjectTeach> subjectTeaches = subjectTeachService.finAllSubjectTeachs();
        List<Subject> subjects = subjectService.finAllSubjects();
        model.addAttribute("subjects", subjects);
        model.addAttribute("subjectTeaches", subjectTeaches);
        return "teacher/subject";
    }

    @RequestMapping("/user")
    public String getAllUser(HttpServletRequest request, Model model) {
//        User user1 = (User) request.getSession().getAttribute("user");
//        if (user1 == null || !user1.getUserRole().equalsIgnoreCase("admin"))
//            return "redirect:/";
        List<User> users = userService.finAllUsers();
        if (users != null)
            model.addAttribute("users", users);
        return "admin/panel/panelUser";
    }

    @RequestMapping("/user/add")
    public String addNewUser(@ModelAttribute("user") User user, HttpServletRequest request) {
//        User user1 = (User) request.getSession().getAttribute("user");
//        if (user1 == null || !user1.getUserRole().equalsIgnoreCase("admin"))
//            return "redirect:/";
        String userRole = request.getParameter("userRole");
        if (user.getUserFullName() != null && user.getUserPassword() != null
                && user.getUserEmail() != null && userRole != null) {
            long count = userService.finAllUsers().size();
            String userId = "USID" + (++count);
            User newUser = new User(userId, user.getUserFullName(), user.getUserPassword(), userRole, user.getUserEmail());
            userService.addUser(newUser);
            return "redirect:/user";
        }
        return "admin/add/addNewUser";
    }

    @RequestMapping("/user/edit/{id}")
    public String updateUser(@PathVariable String id, @ModelAttribute("user") User user, HttpServletRequest request, Model model) {
//        User user1 = (User) request.getSession().getAttribute("user");
//        if (user1 == null || !user1.getUserRole().equalsIgnoreCase("admin"))
//            return "redirect:/";
        user = userService.findUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("admin", "Admin");
        model.addAttribute("teacher", "Teacher");
        model.addAttribute("student", "Student");
        return "admin/update/updateUser";
    }

    @RequestMapping("/user/update/{id}")
    public String update(@PathVariable String id, @ModelAttribute("user") @Valid User user, HttpServletRequest request) {
//        User user1 = (User) request.getSession().getAttribute("user");
//        if (user1 == null || !user1.getUserRole().equalsIgnoreCase("admin"))
//            return "redirect:/";
        User updateUser = userService.findUserById(id);
        if (user.getId() != null && user.getUserFullName() != null && user.getUserPassword() != null
                && user.getUserEmail() != null && user.getUserRole() != null) {
            updateUser.setUserFullName(user.getUserFullName());
            updateUser.setUserPassword(user.getUserPassword());
            updateUser.setUserRole(user.getUserRole());
            updateUser.setId(id);
            updateUser.setUserEmail(user.getUserEmail());
            userService.updateUser(updateUser);
            return "redirect:/user";
        }
        return "admin/update/updateUser";
    }

    //    deleting the userid
    @RequestMapping("/user/delete/{id}")
    public String delete(@PathVariable String id) {
        try {
            userService.deleteUser(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/user";
    }

}
