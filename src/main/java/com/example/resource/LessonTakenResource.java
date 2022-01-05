package com.example.resource;

import com.example.model.LessonTaken;
import com.example.service.LessonTakenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/lessonTaken")
public class LessonTakenResource {
    private final LessonTakenService lessonTakenService;

    public LessonTakenResource(LessonTakenService lessonTakenService) {
        this.lessonTakenService = lessonTakenService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<LessonTaken>> getAllLessonTaken() {
        List<LessonTaken> lessonTaken = lessonTakenService.findAllLessonTaken();
        return new ResponseEntity<>(lessonTaken, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<LessonTaken> getLessonTakenById(@PathVariable("id") String id) {
        LessonTaken lessonTaken = lessonTakenService.findLessonTakenById(id);
        return new ResponseEntity<>(lessonTaken, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<LessonTaken> addLessonTaken(@RequestBody LessonTaken lessonTaken) {
        LessonTaken lessonTaken1 = lessonTakenService.addLessonTaken(lessonTaken);
        return new ResponseEntity<>(lessonTaken1, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<LessonTaken> updateLessonTaken(@RequestBody LessonTaken lessonTaken) {
        LessonTaken updateLessonTaken = lessonTakenService.updateLessonTaken(lessonTaken);
        return new ResponseEntity<>(updateLessonTaken, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLessonTaken(@PathVariable("id") String id) {
//        LessonTaken lessonTaken= lessonTakenService.findLessonTakenById(id);
//        System.out.println(lessonTaken.getId().getSubjectId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
