package com.example.controller;

import com.example.model.*;
import com.example.repo.StudentRepo;
import com.example.repo.SubjectRepo;
import com.example.service.*;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Controller
public class ExamController {
    private StudentService studentService;
    private QuestionService questionService;
    private AnswerService answerService;
    private LessonTakenService lessonTakenService;
    private ExamService examService;
    private SubjectService subjectService;
    private SubjectTeachService subjectTeachService;
    private TeacherService teacherService;
    private AnswerSheetService answerSheetService;

    public ExamController(StudentService studentService, QuestionService questionService, AnswerService answerService,
                          SubjectTeachService subjectTeachService, LessonTakenService lessonTakenService, ExamService examService,
                          SubjectService subjectService, TeacherService teacherService, AnswerSheetService answerSheetService) {
        this.studentService = studentService;
        this.questionService = questionService;
        this.answerService = answerService;
        this.lessonTakenService = lessonTakenService;
        this.examService = examService;
        this.subjectService = subjectService;
        this.subjectTeachService = subjectTeachService;
        this.teacherService = teacherService;
        this.answerSheetService = answerSheetService;
    }

    @RequestMapping("/exam/{id}")
    public String exam(@PathVariable String id, Model model, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getUserRole().equalsIgnoreCase("student"))
            return "redirect:/";
        List<Question> questions = questionService.findAllQuestionBySubjectID(id);
        List<String> answersKey = new ArrayList<>();
        Cookie[] cookies = request.getCookies();
        for (Question question : questions)
            if (cookies != null)
                for (Cookie cookie : cookies)
                    if (cookie.getName().equals(question.getId())) {
                        cookie.setPath("/exam");
                        answersKey.add(cookie.getValue());
                    }
        List<Answer> answers = new ArrayList<>();
        Random random = new Random();
        for (Question question : questions) {
            List<Answer> answerList = answerService.findAllAnswerByQuestionId(question);
            ArrayList arrayList = new ArrayList();
            int temp = 1;
            for (int i = 0; i < 4; i++) {
                while (arrayList.contains(temp))
                    temp = Math.abs(random.nextInt() % 4);
                arrayList.add(i, temp);
            }
            for (int i = 0; i < 4; i++)
                answers.add(answerList.get(Integer.parseInt(arrayList.get(i).toString())));
        }
        model.addAttribute("questions", questions);
        model.addAttribute("answers", answers);
        model.addAttribute("midterm", "Midterm");
        model.addAttribute("id", id);
        model.addAttribute("answerKeys",answersKey);
        return "student/exam";
    }

    @RequestMapping("/teacher/exam/{id}")
    public String teacherExamPanel(@PathVariable String id, Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getUserRole().equalsIgnoreCase("teacher"))
            return "redirect:/";
        List<Exam> exams = examService.findALLExamBySubjectId(subjectService.findSubjectById(id));
        List<Subject> subjects = subjectService.finAllSubjects();
        model.addAttribute("subjects", subjects);
        model.addAttribute("exams", exams);
        model.addAttribute("id", id);
        return "teacher/exam";
    }

    @RequestMapping("/teacher/exam/add/{id}")
    public String addExam(@PathVariable String id, Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getUserRole().equalsIgnoreCase("teacher"))
            return "redirect:/";
        Teacher teacher = teacherService.findTeacherByEmail(user.getUserEmail());
        List<SubjectTeach> subjectTeaches = subjectTeachService.findAllSubjectTeachByTeacherId(teacher.getId());
        for (SubjectTeach subjectTeach : subjectTeaches) {
            if (subjectTeach.getId().getSubjectId().equalsIgnoreCase(id)) {
                Subject subject = subjectService.findSubjectById(id);
                model.addAttribute("subject", subject);
                String term = request.getParameter("term");
                String examDateTime = request.getParameter("examDate");
                String examDuration = request.getParameter("examDuration");
                LocalDateTime localDateTime = null;
                long count = examService.findAllExams().size();
                String examId = "EXAM" + (++count);
                if (examDateTime != null)
                    localDateTime = LocalDateTime.parse(examDateTime);

                if (localDateTime != null && term != null && examDuration != null) {

                    Exam exam = new Exam();
                    exam.setId(examId);
                    exam.setSubject(subject);
                    exam.setExamTerm(term);
                    exam.setExamDateTime(localDateTime.toInstant(ZoneOffset.UTC));
                    exam.setDuration(Integer.valueOf(examDuration));
                    examService.addNewExam(exam);
                    return "redirect:/teacher/exam/" + id;
                }
                return "teacher/addExam";
            }
        }
        return "redirect:/";
    }

    @RequestMapping("/teacher/exam/edit/{id}")
    public String editExam(@PathVariable String id, Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getUserRole().equalsIgnoreCase("teacher"))
            return "redirect:/";
        Teacher teacher = teacherService.findTeacherByEmail(user.getUserEmail());
        List<SubjectTeach> subjectTeaches = subjectTeachService.findAllSubjectTeachByTeacherId(teacher.getId());
        Exam exam = examService.findExamById(id);
        for (SubjectTeach subjectTeach : subjectTeaches) {
            if (subjectTeach.getId().getSubjectId().equalsIgnoreCase(exam.getSubject().getId())) {
                if (id != null && exam != null) {
                    model.addAttribute("exam", exam);
                    model.addAttribute("fin", "final");
                    model.addAttribute("mid", "midterm");
                    return "teacher/updateExam";
                }
            }
        }
        return "redirect:/";
    }

    @RequestMapping("/teacher/exam/delete/{id}")
    public String deleteExam(@PathVariable String id, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getUserRole().equalsIgnoreCase("teacher"))
            return "redirect:/";
        Teacher teacher = teacherService.findTeacherByEmail(user.getUserEmail());
        List<SubjectTeach> subjectTeaches = subjectTeachService.findAllSubjectTeachByTeacherId(teacher.getId());
        Exam exam = examService.findExamById(id);
        for (SubjectTeach subjectTeach : subjectTeaches) {
            if (subjectTeach.getId().getSubjectId().equalsIgnoreCase(exam.getSubject().getId())) {
                examService.deleteExamById(id);
                return "redirect:/teacher/exam/" + exam.getSubject().getId();
            }
        }
        return "redirect:/";
    }

    @RequestMapping("/teacher/exam/update/{id}")
    public String updateExam(@PathVariable String id, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getUserRole().equalsIgnoreCase("teacher"))
            return "redirect:/";
        Teacher teacher = teacherService.findTeacherByEmail(user.getUserEmail());
        List<SubjectTeach> subjectTeaches = subjectTeachService.findAllSubjectTeachByTeacherId(teacher.getId());
        Exam exam = examService.findExamById(id);
        for (SubjectTeach subjectTeach : subjectTeaches) {
            if (subjectTeach.getId().getSubjectId().equalsIgnoreCase(exam.getSubject().getId())) {
                String term = request.getParameter("term");
                String examDateTime = request.getParameter("examDate");
                String examDuration = request.getParameter("examDuration");
                LocalDateTime localDateTime = null;
                long count = examService.findAllExams().size();
                if (examDateTime != null)
                    localDateTime = LocalDateTime.parse(examDateTime);
                if (localDateTime != null && term != null && examDuration != null) {
                    exam.setId(id);
                    exam.setExamTerm(term);
                    exam.setExamDateTime(localDateTime.toInstant(ZoneOffset.UTC));
                    exam.setSubject(exam.getSubject());
                    exam.setDuration(Integer.valueOf(examDuration));
                    examService.updateExam(exam);
                    return "redirect:/teacher/exam/" + exam.getSubject().getId();
                }
                return "teacher/updateExam";
            }
        }
        return "redirect:/";
    }

    @RequestMapping("/student/exam")
    public String examPanel(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getUserRole().equalsIgnoreCase("student"))
            return "redirect:/";

//        find student by user email
        Student student = studentService.findStudentByStudentEmail(user.getUserEmail());
//        get all lesson takes by student
        List<LessonTaken> lessonTakes = lessonTakenService.findAllLessonTakenByStudentId(student.getStudentId());

        List<Subject> subjects = new ArrayList<>();
        String examButton = "disabled";
        String color = "none";
        String buttonVa = "Waiting";
        List<Exam> exams = new ArrayList<>();
        HashMap<String, String> examButtons = new HashMap<>();
        HashMap<String, String> buttonValue = new HashMap<>();
        HashMap<String, String> buttonColor = new HashMap<>();
        for (LessonTaken lessonTaken : lessonTakes) {
            subjects.add(lessonTaken.getSubject());
            List<Exam> examList = examService.findALLExamBySubjectId(lessonTaken.getSubject());
            for (Exam exam : examList) {
                if (lessonTaken.getSubject().equals(exam.getSubject())) {
                    exams.add(exam);
                    if (exam.getExamDateTime().isAfter(LocalDateTime.now().toInstant(ZoneOffset.MAX))) {
//                    counting time
                        LocalDateTime examDateTime = LocalDateTime.ofInstant(exam.getExamDateTime(), ZoneOffset.UTC);
                        Duration duration = Duration.between(LocalDateTime.now(), examDateTime);
                        long days = (duration.getSeconds() / (60 * 60 * 24));
                        long hours = (duration.getSeconds() % (60 * 60 * 24) / (3600));
                        long minute = (duration.getSeconds() % (60 * 60 * 24) % 3600) / (60);
                        System.out.println("days " + days);
                        System.out.println("hours " + hours);
                        System.out.println("minutes " + minute);
                        if (days == 0 && hours == 0 && minute <= 0 && minute >= -exam.getDuration()) {
                            examButton = "enabled";
                            color = "green";
                            buttonVa = "Start";
                        } else if (days >= 0 && hours >= 0 && minute >= 0) {
                            examButton = "disabled";
                            color = "none";
                            buttonVa = "Waiting";
                        } else {
                            examButton = "disabled";
                            color = "red";
                            buttonVa = "Finished";

                        }
                        System.out.println("After ");

                    } else {
                        examButton = "disabled";
                        buttonVa = "Finished";
                        color = "red";
                        System.out.println("before");
                    }
                    examButtons.put(exam.getSubject().getId(), examButton);
                    buttonValue.put(exam.getSubject().getId(), buttonVa);
                    buttonColor.put(exam.getSubject().getId(), color);
                    System.out.println(exam.getSubject().getId() + "   " + examButton);
                }
            }
        }
        model.addAttribute("subjects", subjects);
        model.addAttribute("exams", exams);
        model.addAttribute("examButtons", examButtons);
        model.addAttribute("buttonColor", buttonColor);
        model.addAttribute("buttonValue", buttonValue);
        return "student/examPanel";
    }
}
