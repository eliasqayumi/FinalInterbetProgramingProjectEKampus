package com.example.resource;


import com.example.model.StudentInfo;
import com.example.service.StudentInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/studentInfo")
public class StudentInfoResource {
    private final StudentInfoService studentInfoService;

    public StudentInfoResource(StudentInfoService studentInfoService) {
        this.studentInfoService = studentInfoService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentInfo>> getAllStudentInfo() {
        List<StudentInfo> studentInfos = studentInfoService.findAllStudentInfo();
        return new ResponseEntity<>(studentInfos, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<StudentInfo> getStudentInfoById(@PathVariable("id") String id) {
        StudentInfo studentInfo = studentInfoService.findStudentInfoById(id);
        return new ResponseEntity<>(studentInfo, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<StudentInfo> addStudentInfo(@RequestBody StudentInfo studentInfo) {
        StudentInfo studentInfo1 = studentInfoService.addStudentInfo(studentInfo);
        return new ResponseEntity<>(studentInfo1, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<StudentInfo> updateStudentInfo(@RequestBody StudentInfo studentInfo) {
        StudentInfo updateStudentInfo = studentInfoService.updateStudentInfo(studentInfo);
        return new ResponseEntity<>(updateStudentInfo, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudentInfo(@PathVariable("id") String id) {
        studentInfoService.deleteStudentInfo(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
