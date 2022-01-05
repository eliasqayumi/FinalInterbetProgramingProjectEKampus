package com.example.resource;

import com.example.model.Subject;
import com.example.service.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/subject")
public class SubjectResource {
    private final SubjectService subjectService;

    public SubjectResource(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Subject>> getAllSubject() {
        List<Subject> subjects = subjectService.finAllSubjects();
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable("id") String id) {
        Subject subject = subjectService.findSubjectById(id);
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Subject> addSubject(@RequestBody Subject subject) {
        Subject subject1 = subjectService.addSubject(subject);
        return new ResponseEntity<>(subject1, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Subject> updateSubject(@RequestBody Subject subject) {
        Subject updateSubject = subjectService.updateSubject(subject);
        return new ResponseEntity<>(updateSubject, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable("id") String id) {
        subjectService.deleteSubject(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
