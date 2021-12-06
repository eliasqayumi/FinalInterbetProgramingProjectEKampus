package com.example.service;

import com.example.exception.NotFoundException;
import com.example.model.TeacherInfo;
import com.example.repo.TeacherInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherInfoService {
    private TeacherInfoRepo teacherInfoRepo;

    @Autowired
    public TeacherInfoService(TeacherInfoRepo teacherInfoRepo) {
        this.teacherInfoRepo = teacherInfoRepo;
    }

    public TeacherInfo addTeacherInfo(TeacherInfo teacherInfo) {
        return teacherInfoRepo.save(teacherInfo);
    }

    public List<TeacherInfo> finAllTeacherInfos() {
        return teacherInfoRepo.findAll();
    }

    public TeacherInfo updateTeacherInfo(TeacherInfo teacherInfo) {
        return teacherInfoRepo.save(teacherInfo);
    }
    public TeacherInfo findTeacherInfoById(String id) {
        return teacherInfoRepo.findTeacherInfoById(id).orElseThrow(() -> new NotFoundException("TeacherInfo by id " + id + " was no found"));
    }


    public void deleteTeacherInfo(String id) {
        teacherInfoRepo.deleteTeacherInfoById(id);
    }


}
