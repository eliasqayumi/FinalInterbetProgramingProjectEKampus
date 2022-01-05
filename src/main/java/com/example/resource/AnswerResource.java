package com.example.resource;

import com.example.model.Answer;
import com.example.service.AnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/answer")
public class AnswerResource {
    private final AnswerService answerService;

    public AnswerResource(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Answer>> getAllAnswer() {
        List<Answer> answers = answerService.findAllAnswer();
        return new ResponseEntity<>(answers, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Answer> getAnswerById(@PathVariable("id") String id) {
        Answer answer = answerService.findAnswerById(id);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Answer> addAnswer(@RequestBody Answer answer) {
        Answer answer1 = answerService.addAnswer(answer);
        return new ResponseEntity<>(answer1, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Answer> updateAnswer(@RequestBody Answer answer) {
        Answer updateAnswer = answerService.updateAnswer(answer);
        return new ResponseEntity<>(updateAnswer, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable("id") String id) {
        answerService.deleteAnswer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
