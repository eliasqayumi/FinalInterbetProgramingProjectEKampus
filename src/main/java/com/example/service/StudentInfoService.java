package com.example.service;

import com.example.exception.NotFoundException;
import com.example.model.StudentInfo;
import com.example.repo.StudentInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentInfoService {
    private StudentInfoRepo StudentInfoRepo;

    @Autowired
    public StudentInfoService(StudentInfoRepo StudentInfoRepo) {
        this.StudentInfoRepo = StudentInfoRepo;
    }
    public List<StudentInfo> findAllStudentInfo(){
        return StudentInfoRepo.findAll();
    }
    public StudentInfo addStudentInfo(StudentInfo StudentInfo){
        return StudentInfoRepo.save(StudentInfo);
    }
    public StudentInfo updateStudentInfo(StudentInfo StudentInfo){
        return StudentInfoRepo.save(StudentInfo);
    }
    public void deleteStudentInfo(String id) {
        StudentInfoRepo.deleteStudentInfoById(id);
    }

    public StudentInfo findStudentInfoById(String id) {
        return StudentInfoRepo.findStudentInfoById(id)
                .orElseThrow(() -> new NotFoundException("User by id " + id + " was no found"));
    }
    
}
