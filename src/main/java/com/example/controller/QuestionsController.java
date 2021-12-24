package com.example.controller;

import com.example.model.*;
import com.example.service.*;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class QuestionsController {
    private QuestionService questionService;
    private AnswerService answerService;
    private SubjectService subjectService;


    public QuestionsController(QuestionService questionService, AnswerService answerService,
                               SubjectService subjectService) {
        this.questionService = questionService;
        this.answerService = answerService;
        this.subjectService = subjectService;

    }

    @RequestMapping("/question/{id}")
    public String getAllQuestion(@PathVariable String id, Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getUserRole().toLowerCase().equals("teacher"))
            return "redirect:/";
        model.addAttribute("id", id);
        List<Question> questions = questionService.findAllQuestionBySubjectID(id);
        List<Answer> answers = new ArrayList<>();
        ArrayList<Answer> answerList = new ArrayList<>();
        for (Question question : questions) {
            answers = answerService.findAllAnswerByQuestionId(question);
            for (Answer answer : answers)
                answerList.add(answer);
        }
        if (questions != null)
            model.addAttribute("questions", questions);
        if (answerList != null)
            model.addAttribute("answersList", answerList);
        return "teacher/panelQuestion";
    }

    @RequestMapping("/question/add/{id}")
    public String addNewQuestion(@PathVariable String id, Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getUserRole().toLowerCase().equals("teacher"))
            return "redirect:/";
        if (id != null) {
            Subject subject = subjectService.findSubjectById(id);
            model.addAttribute("subject", subject);
            String question1 = request.getParameter("question");
            String term = request.getParameter("term");
            String answer1 = request.getParameter("answer1");
            String answer2 = request.getParameter("answer2");
            String answer3 = request.getParameter("answer3");
            String answer4 = request.getParameter("answer4");


            long questionId = questionService.findAllQuestion().size();
            long answerId = answerService.findAllAnswer().size();
            if (question1 != null && answer1 != null && answer2 != null && answer3 != null && answer4 != null && term != null) {
                // add question table
                Question question = new Question();
                question.setId(String.valueOf(++questionId));
                question.setQuestion(question1);
                question.setTerm(term);
                question.setSubject(subject);
                questionService.addQuestion(question);

                // first answer
                // add answer to answer table
                Answer answer = new Answer();
                answer.setId(String.valueOf(++answerId));
                answer.setAnswer(answer1);
                answer.setStatus("false");
                answer.setQuestion(question);
                answerService.addAnswer(answer);

                // second answer false answer
                answer.setId(String.valueOf(++answerId));
                answer.setAnswer(answer2);
                answer.setStatus("false");
                answer.setQuestion(question);
                answerService.addAnswer(answer);

                // third answer  false answer
                answer.setId(String.valueOf(++answerId));
                answer.setAnswer(answer3);
                answer.setStatus("false");
                answer.setQuestion(question);
                answerService.addAnswer(answer);

                // fourth answer true answer
                answer.setId(String.valueOf(++answerId));
                answer.setAnswer(answer4);
                answer.setStatus("true");
                answer.setQuestion(question);
                answerService.addAnswer(answer);
                return "redirect:/question/" + id;
            }
        }
        return "teacher/addNewQuestion";
    }

    @RequestMapping("/question/edit/{id}")
    public String updateQuestion(@PathVariable String id, @ModelAttribute("question") Question question, HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getUserRole().toLowerCase().equals("teacher"))
            return "redirect:/";
        question = questionService.findQuestionById(id);
        List<Answer> answers = answerService.findAllAnswerByQuestionId(question);
        model.addAttribute("question", question);
        model.addAttribute("answers", answers);
        return "teacher/updateQuestion";
    }

    @RequestMapping("/question/update/{id}")
    public String update(@PathVariable String id, @ModelAttribute("question") @Valid Question question, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getUserRole().toLowerCase().equals("teacher"))
            return "redirect:/";
        Question updatedQuestion = questionService.findQuestionById(id);
        if (question.getId() != null && question.getQuestion() != null && question.getTerm() != null && question.getSubject() != null) {
            updatedQuestion.setId(id);
            updatedQuestion.setQuestion(question.getQuestion());
            updatedQuestion.setTerm(question.getTerm());
            updatedQuestion.setSubject(question.getSubject());
            questionService.updateQuestion(updatedQuestion);
            return "redirect:/question";
        }
        return "teacher/updateQuestion";
    }

    @RequestMapping("/question/delete/{id}")
    public String delete(@PathVariable String id, HttpServletRequest request) {
        Question question = questionService.findQuestionById(id);
        String subjectId = question.getSubject().getId();
        List<Answer> answers = answerService.findAllAnswerByQuestionId(question);
        for (Answer answer : answers)
            answerService.deleteAnswer(answer.getId());
        questionService.deleteQuestion(question.getId());
        return "redirect:/question/" + subjectId;
    }
}
