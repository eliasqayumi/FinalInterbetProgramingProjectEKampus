package com.example.service;

import com.example.exception.NotFoundException;
import com.example.model.Teacher;
import com.example.repo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private TeacherRepo teacherRepo;

    @Autowired
    public TeacherService(TeacherRepo teacherRepo) {
        this.teacherRepo = teacherRepo;
    }

    public Teacher addTeacher(Teacher teacher) {
        return teacherRepo.save(teacher);
    }

    public List<Teacher> finAllTeachers() {
        return teacherRepo.findAll();
    }

    public Teacher updateTeacher(Teacher teacher) {
        return teacherRepo.save(teacher);
    }
    public Teacher findTeacherById(String id) {
        return teacherRepo.findTeacherById(id).orElseThrow(() -> new NotFoundException("User by id " + id + " was no found"));
    }


    public void deleteTeacher(String id) {
        teacherRepo.deleteTeacherById(id);
    }

}
