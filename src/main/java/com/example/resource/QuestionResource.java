package com.example.resource;

import com.example.model.Question;
import com.example.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/question")
public class QuestionResource {
    private final QuestionService questionService;

    public QuestionResource(QuestionService QuestionService) {
        this.questionService = QuestionService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Question>> getAllQuestion() {
        List<Question> questions = questionService.findAllQuestion();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable("id") String id) {
        Question question = questionService.findQuestionById(id);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        Question question1 = questionService.addQuestion(question);
        return new ResponseEntity<>(question1, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
        Question updateQuestion = questionService.updateQuestion(question);
        return new ResponseEntity<>(updateQuestion, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable("id") String id) {
        questionService.deleteQuestion(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
