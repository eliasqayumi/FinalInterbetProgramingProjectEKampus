package com.example.service;

import com.example.exception.NotFoundException;
import com.example.model.Subject;
import com.example.repo.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    private SubjectRepo subjectRepo;

    @Autowired
    public SubjectService(SubjectRepo SubjectRepo) {
        this.subjectRepo = SubjectRepo;
    }

    public Subject addSubject(Subject Subject) {
        return subjectRepo.save(Subject);
    }

    public List<Subject> finAllSubjects() {
        return subjectRepo.findAll();
    }

    public Subject updateSubject(Subject Subject) {
        return subjectRepo.save(Subject);
    }
    public Subject findSubjectById(String id) {
        return subjectRepo.findSubjectById(id).orElseThrow(() -> new NotFoundException("Subject by id " + id + " was no found"));
    }
    public void deleteSubject(String id) {
        subjectRepo.deleteSubjectById(id);
    }

    
    
}
