package com.example.resource;


import com.example.model.TeacherInfo;
import com.example.service.TeacherInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/TeacherInfo")
public class TeacherInfoResource {
    private final TeacherInfoService teacherInfoService;

    public TeacherInfoResource(TeacherInfoService teacherInfoService) {
        this.teacherInfoService = teacherInfoService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TeacherInfo>> getAllTeacherInfo() {
        List<TeacherInfo> teacherInfos = teacherInfoService.finAllTeacherInfos();
        return new ResponseEntity<>(teacherInfos, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<TeacherInfo> getTeacherInfoById(@PathVariable("id") String id) {
        TeacherInfo teacherInfo = teacherInfoService.findTeacherInfoById(id);
        return new ResponseEntity<>(teacherInfo, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<TeacherInfo> addTeacherInfo(@RequestBody TeacherInfo teacherInfo) {
        TeacherInfo teacherInfo1 = teacherInfoService.addTeacherInfo(teacherInfo);
        return new ResponseEntity<>(teacherInfo1, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<TeacherInfo> updateTeacherInfo(@RequestBody TeacherInfo teacherInfo) {
        TeacherInfo updateTeacherInfo = teacherInfoService.updateTeacherInfo(teacherInfo);
        return new ResponseEntity<>(updateTeacherInfo, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTeacherInfo(@PathVariable("id") String id) {
        teacherInfoService.deleteTeacherInfo(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
