package com.example.resource;

import com.example.model.AnswerSheet;
import com.example.service.AnswerSheetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/answerSheet")
public class AnswerSheetResource {
    private final AnswerSheetService answerSheetService;

    public AnswerSheetResource(AnswerSheetService AnswerSheetService) {
        this.answerSheetService = AnswerSheetService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AnswerSheet>> getAllAnswerSheet() {
        List<AnswerSheet> AnswerSheets = answerSheetService.findAllAnswerSheet();
        return new ResponseEntity<>(AnswerSheets, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<AnswerSheet> getAnswerSheetById(@PathVariable("id") String id) {
        AnswerSheet answerSheet = answerSheetService.findAnswerSheetById(id);
        return new ResponseEntity<>(answerSheet, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<AnswerSheet> addAnswerSheet(@RequestBody AnswerSheet AnswerSheet) {
        AnswerSheet AnswerSheet1 = answerSheetService.addAnswerSheet(AnswerSheet);
        return new ResponseEntity<>(AnswerSheet1, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<AnswerSheet> updateAnswerSheet(@RequestBody AnswerSheet AnswerSheet) {
        AnswerSheet updateAnswerSheet = answerSheetService.updateAnswerSheet(AnswerSheet);
        return new ResponseEntity<>(updateAnswerSheet, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAnswerSheet(@PathVariable("id") String id) {
        answerSheetService.deleteAnswerSheet(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
