package com.example.resource;

import com.example.model.Teacher;
import com.example.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/teacher")
public class TeacherResource {

    private final TeacherService teacherService;

    public TeacherResource(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Teacher>> getAllStudent() {
        List<Teacher> teachers = teacherService.finAllTeachers();
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Teacher> getStudentById(@PathVariable("id") String id) {
        Teacher teacher = teacherService.findTeacherById(id);
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Teacher> addStudent(@RequestBody Teacher teacher) {
        Teacher teacher1 = teacherService.addTeacher(teacher);
        return new ResponseEntity<>(teacher1, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Teacher> updateStudent(@RequestBody Teacher teacher) {
        Teacher updateTeacher = teacherService.updateTeacher(teacher);
        return new ResponseEntity<>(updateTeacher, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") String id) {
        teacherService.deleteTeacherById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
