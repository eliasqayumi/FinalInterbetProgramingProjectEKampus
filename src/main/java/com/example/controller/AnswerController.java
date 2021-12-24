package com.example.controller;

import com.example.model.*;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class AnswerController {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private AnswerService answerService;
    private QuestionService questionService;
    private StudentService studentService;
    private AnswerSheetService answerSheetService;
    private ScoreTableService scoreTableService;


    public AnswerController(AnswerService AnswerService, QuestionService questionService,
                            StudentService studentService, AnswerSheetService answerSheetService, ScoreTableService scoreTableService) {
        this.answerService = AnswerService;
        this.questionService = questionService;
        this.studentService = studentService;
        this.answerSheetService = answerSheetService;
        this.scoreTableService = scoreTableService;
    }

    @RequestMapping("/answer")
    public String getAllAnswer(HttpServletRequest request, Model model) {
        if (request.getSession().getAttribute("user") == null)
            return "redirect:/";
        List<Answer> answers = answerService.findAllAnswer();
        if (answers != null)
            model.addAttribute("answers", answers);
        return "teacher/panelAnswer";
    }

    @RequestMapping("/answer/add")
    public String addNewAnswer(@ModelAttribute("answer") Answer answer, HttpServletRequest request) {
        if (request.getSession().getAttribute("user") == null)
            return "redirect:/";
        if (answer.getId() != null && answer.getAnswer() != null && answer.getStatus() != null && answer.getQuestion() != null) {
            Answer newAnswer = new Answer(answer.getId(), answer.getAnswer(), answer.getStatus(), answer.getQuestion());
            answerService.addAnswer(newAnswer);
            return "teacher/panelAnswer";
        }
        return "teacher/addNewAnswer";
    }

    @RequestMapping("/answer/update/{id}")
    public String updateAnswer(@PathVariable String id, @ModelAttribute("answer") Answer answer, HttpServletRequest request, Model model) {
        if (request.getSession().getAttribute("user") == null)
            return "redirect:/";
        answer = answerService.findAnswerById(id);
        model.addAttribute("answer", answer);
        return "teacher/updateAnswer";
    }

    @RequestMapping("/answer/submit/{id}")
    public String answerSubmit(@PathVariable String id,  HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        Student student = studentService.findStudentByStudentEmail(user.getUserEmail());

        List<Question> questions = questionService.findAllQuestionBySubjectID(id);
        Cookie[] cookies = request.getCookies();
        AnswerSheet answerSheet = null;
        for (Question question : questions) {
            String answerId = answerService.findAnswerById(request.getParameter(question.getId())).getId();
            try {
                answerSheet = answerSheetService
                        .findAnswerSheetByStudentIdAndByQuestionID(student.getStudentId(), question.getId());
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
            if (answerSheet == null) {
                AnswerSheetId answerSheetId = new AnswerSheetId();
                answerSheetId.setStudentId(student.getStudentId());
                answerSheetId.setAnswerId(answerId);
                answerSheetId.setQuestionId(question.getId());
                AnswerSheet newAnswerSheet = new AnswerSheet();
                newAnswerSheet.setId(answerSheetId);
                answerSheetService.addAnswerSheet(newAnswerSheet);
                if (question != null) {
                    if (cookies != null)
                        for (Cookie cookie : cookies)
                            if (!cookie.getName().equals(question.getId())) {
                                Cookie newCookie = new Cookie(question.getId(), answerId);
                                newCookie.setPath("/exam");
                                newCookie.setMaxAge(60 * 50);
                                response.addCookie(newCookie);
                            }
                }
            } else {
                AnswerSheetId answerSheetId = answerSheet.getId();
                answerSheetId.setStudentId(student.getStudentId());
                answerSheetId.setAnswerId(answerId);
                answerSheetId.setQuestionId(question.getId());
                AnswerSheet updateAnswerSheet = answerSheet;
                updateAnswerSheet.setId(answerSheetId);
                answerSheetService.updateAnswerSheet(updateAnswerSheet);
            }
        }
        return "redirect:/student/exam";
    }

    @RequestMapping("/scoreAll/{id}")
    public String score() {
        List<AnswerSheet> answerSheets = answerSheetService.findAllAnswerSheet();

        for (AnswerSheet temp : answerSheets) {

            if (answerService.findAnswerById(temp.getId().getAnswerId()).getStatus().equals("true")) {
                String subjectId = questionService.findQuestionById(temp.getId().getQuestionId()).getSubject().getId();
                String term = questionService.findQuestionById(temp.getId().getQuestionId()).getTerm();
                LocalDate dateTime = LocalDate.now();
                int marks = 10;
                ScoreTableId scoreTableId = new ScoreTableId();
                scoreTableId.setExamName(term);
                scoreTableId.setSubjectId(subjectId);
                scoreTableId.setExamDate(dateTime);
                scoreTableId.setStudentId("1");
                ScoreTable scoreTable = new ScoreTable();
                scoreTable.setScoreId("1");
                scoreTable.setMarks(marks);
                scoreTable.setId(scoreTableId);
                scoreTableService.addScoreTable(scoreTable);
            }
        }
        return "student/scores";
    }

}
