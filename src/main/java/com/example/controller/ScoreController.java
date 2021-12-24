package com.example.controller;

import com.example.model.*;
import com.example.repo.StudentRepo;
import com.example.repo.SubjectRepo;
import com.example.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class ScoreController {
    private ScoreTableService scoreTableService;
    private ExamService examService;
    private SubjectService subjectService;
    private TeacherService teacherService;
    private SubjectTeachService subjectTeachService;
    private QuestionService questionService;
    private AnswerService answerService;
    private AnswerSheetService answerSheetService;
    private StudentService studentService;
    private LessonTakenService lessonTakenService;

    public ScoreController(ScoreTableService scoreTableService, ExamService examService, SubjectService subjectService, LessonTakenService lessonTakenService,
                           AnswerService answerService, TeacherService teacherService, SubjectTeachService subjectTeachService,
                           QuestionService questionService, AnswerSheetService answerSheetService, StudentService studentService) {
        this.scoreTableService = scoreTableService;
        this.examService = examService;
        this.subjectService = subjectService;
        this.teacherService = teacherService;
        this.subjectTeachService = subjectTeachService;
        this.questionService = questionService;
        this.answerService = answerService;
        this.answerSheetService = answerSheetService;
        this.studentService = studentService;
        this.lessonTakenService = lessonTakenService;
    }

    //  teacher score panel
    @RequestMapping("/score")
    public String getAllScore(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getUserRole().equalsIgnoreCase("teacher"))
            return "redirect:/";
        List<Exam> exams = new ArrayList<>();
        Teacher teacher = teacherService.findTeacherByEmail(user.getUserEmail());
        List<SubjectTeach> subjectTeaches = subjectTeachService.findAllSubjectTeachByTeacherId(teacher.getId());
        for (SubjectTeach subjectTeach : subjectTeaches)
            try {
                exams.add(examService.findExamBySubject(subjectService.findSubjectById(subjectTeach.getId().getSubjectId())));
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        List<Subject> subjects = subjectService.finAllSubjects();
        model.addAttribute("subjects", subjects);
        model.addAttribute("subjectTeaches", subjects);
        model.addAttribute("teacher", teacher);
        model.addAttribute("exams", exams);
        return "student/panelScore";
    }

    //  teacher press teh score button use for
    @RequestMapping("/teacher/score/{id}")
    public String scoreExams(@PathVariable String id) {
        System.out.println(id);
        Exam exam = examService.findExamById(id);
        if (exam != null) {
            Subject subject = subjectService.findSubjectById(exam.getSubject().getId());
            List<LessonTaken> lessonTakes = lessonTakenService.findAllBySubjectId(subject.getId());
//           get all question belong to the subject by subject id
            List<Question> questions = questionService.findAllQuestionBySubjectID(subject.getId());
//            get the size of question numbers use for scoring like 100/questionNumbers
            double questionNumbers = questionService.findAllBySubjectIdAndTerm(subject.getId(), exam.getExamTerm()).size();
            double sco = 100 / questionNumbers;
            short trueAnswer = 0;
//            keep the all true answer
            List<Answer> answers = new ArrayList<>();
            for (LessonTaken lessonTaken : lessonTakes) {
                for (Question question : questions)
                    if (question.getTerm().equalsIgnoreCase(exam.getExamTerm())) {
                        List<Answer> answerList = answerService.findAllAnswerByQuestionId(question);
                        for (Answer answer : answerList)
                            if (answer.getStatus().equalsIgnoreCase("true"))
                                answers.add(answer);
                        List<AnswerSheet> answerSheets = answerSheetService.findAllAnswerSheetByStudentId(lessonTaken.getStudent().getStudentId());
                        if (answerSheets != null)
                            for (AnswerSheet answerSheet : answerSheets)
                                if (question.getId().equalsIgnoreCase(answerSheet.getId().getQuestionId()))
                                    for (Answer answer : answers)
                                        if (answer.getId().equalsIgnoreCase(answerSheet.getId().getAnswerId()))
                                            trueAnswer++;
                    }
                long count = scoreTableService.findAllScoreTable().size();
                String scoreId = "SC" + (++count);
                ScoreTableId scoreTableId = new ScoreTableId();
                scoreTableId.setStudentId(lessonTaken.getStudent().getStudentId());
                scoreTableId.setExamDate(LocalDate.ofInstant(exam.getExamDateTime(), ZoneOffset.UTC));
                scoreTableId.setSubjectId(subject.getId());
                scoreTableId.setExamName(exam.getSubject().getSubjectName() + " " + exam.getExamTerm());
                ScoreTable scoreTable = new ScoreTable();
                scoreTable.setScoreId(scoreId);
                scoreTable.setId(scoreTableId);
                scoreTable.setMarks((int) sco * trueAnswer);
                scoreTableService.addScoreTable(scoreTable);
            }
        }

        return "redirect:/score";
    }

    @RequestMapping("/score/add")
    public String addNewScore(@ModelAttribute("score") ScoreTable scoreTable, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getUserRole().toLowerCase().equals("teacher"))
            return "redirect:/";
        if (scoreTable.getScoreId() != null && scoreTable.getId().getStudentId() != null && scoreTable.getId().getSubjectId() != null &&
                scoreTable.getMarks() != null && scoreTable.getId().getExamDate() != null && scoreTable.getId().getExamName() != null) {
            ScoreTable newScore = new ScoreTable();
            newScore.setScoreId(scoreTable.getScoreId());
            newScore.getId().setStudentId(scoreTable.getId().getStudentId());
            newScore.getId().setSubjectId(scoreTable.getId().getSubjectId());
            newScore.setMarks(scoreTable.getMarks());
            newScore.getId().setExamName(scoreTable.getId().getExamName());
            newScore.getId().setExamDate(scoreTable.getId().getExamDate());
            scoreTableService.addScoreTable(newScore);
            return "panelScore";
        }
        return "addNewScore";
    }

    @RequestMapping("/score/update/{id}")
    public String updateScore(@PathVariable String id, @ModelAttribute("score") ScoreTable scoreTable, HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getUserRole().toLowerCase().equals("teacher"))
            return "redirect:/";
        scoreTable = scoreTableService.findScoreTableById(id);
        model.addAttribute("score", scoreTable);
        return "updateScore";
    }

    public String update(@PathVariable String id, @ModelAttribute("Score") @Valid ScoreTable scoreTable, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getUserRole().toLowerCase().equals("student"))
            return "redirect:/";
        ScoreTable updatedScore = scoreTableService.findScoreTableById(id);
        if (scoreTable.getScoreId() != null && scoreTable.getId().getStudentId() != null && scoreTable.getId().getSubjectId() != null &&
                scoreTable.getMarks() != null && scoreTable.getId().getExamDate() != null && scoreTable.getId().getExamName() != null) {
            updatedScore.setScoreId(scoreTable.getScoreId());
            updatedScore.getId().setStudentId(scoreTable.getId().getStudentId());
            updatedScore.getId().setSubjectId(scoreTable.getId().getSubjectId());
            updatedScore.setMarks(scoreTable.getMarks());
            updatedScore.getId().setExamName(scoreTable.getId().getExamName());
            updatedScore.getId().setExamDate(scoreTable.getId().getExamDate());
            scoreTableService.addScoreTable(updatedScore);
            scoreTableService.updateScoreTable(updatedScore);
            return "panelScore";
        }
        return "updateScore";
    }
    //  Student panel for score
    @RequestMapping("/student/score")
    public String studentScore(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getUserRole().toLowerCase().equals("student"))
            return "redirect:/";
        Student student = studentService.findStudentByStudentEmail(user.getUserEmail());
        List<LessonTaken> lessonTakes = lessonTakenService.findAllLessonTakenByStudentId(student.getStudentId());
        List<ScoreTable> scoreTables = scoreTableService.findAllScoreTableByStudentId(student.getStudentId());
        model.addAttribute("lessonTakes", lessonTakes);
        model.addAttribute("scoreTables", scoreTables);
        return "student/scores";
    }

}
