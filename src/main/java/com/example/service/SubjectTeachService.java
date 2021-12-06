package com.example.service;

import com.example.exception.NotFoundException;
import com.example.model.SubjectTeach;
import com.example.repo.SubjectTeachRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectTeachService {
    private SubjectTeachRepo subjectTeachRepo;

    @Autowired
    public SubjectTeachService(SubjectTeachRepo subjectTeachRepo) {
        this.subjectTeachRepo = subjectTeachRepo;
    }

    public SubjectTeach addSubjectTeach(SubjectTeach subjectTeach) {
        return subjectTeachRepo.save(subjectTeach);
    }

    public List<SubjectTeach> finAllSubjectTeachs() {
        return subjectTeachRepo.findAll();
    }

    public SubjectTeach updateSubjectTeach(SubjectTeach subjectTeach) {
        return subjectTeachRepo.save(subjectTeach);
    }
    public SubjectTeach findSubjectTeachById(String id) {
        return subjectTeachRepo.findSubjectTeachById(id).orElseThrow(() -> new NotFoundException("SubjectTeach by id " + id + " was no found"));
    }

    public void deleteSubjectTeach(String id) {
        subjectTeachRepo.deleteSubjectTeachById(id);
    }
}
