package com.example.resource;

import com.example.model.SubjectTeach;
import com.example.service.SubjectTeachService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/SubjectTeach")
public class SubjectTeachResource {
    private final SubjectTeachService subjectTeachService;

    public SubjectTeachResource(SubjectTeachService subjectTeachService) {
        this.subjectTeachService = subjectTeachService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<SubjectTeach>> getAllSubjectTeach() {
        List<SubjectTeach> subjectTeaches = subjectTeachService.finAllSubjectTeachs();
        return new ResponseEntity<>(subjectTeaches, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<SubjectTeach> getSubjectTeachById(@PathVariable("id") String id) {
        SubjectTeach subjectTeach = subjectTeachService.findSubjectTeachById1(id);
        return new ResponseEntity<>(subjectTeach, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<SubjectTeach> addSubjectTeach(@RequestBody SubjectTeach subjectTeach) {
        SubjectTeach subjectTeach1 = subjectTeachService.addSubjectTeach(subjectTeach);
        return new ResponseEntity<>(subjectTeach1, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<SubjectTeach> updateSubjectTeach(@RequestBody SubjectTeach subjectTeach) {
        SubjectTeach updateSubjectTeach = subjectTeachService.updateSubjectTeach(subjectTeach);
        return new ResponseEntity<>(updateSubjectTeach, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSubjectTeach(@PathVariable("id") String id) {
        subjectTeachService.deleteSubjectTeach(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
