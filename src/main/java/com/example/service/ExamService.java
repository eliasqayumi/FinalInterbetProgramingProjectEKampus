package com.example.service;

import com.example.exception.NotFoundException;
import com.example.model.Exam;
import com.example.model.Subject;
import com.example.repo.ExamRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExamService {
    private ExamRepo examRepo;

    public ExamService(ExamRepo examRepo) {
        this.examRepo = examRepo;
    }

    public List<Exam> findAllExams() {
        return examRepo.findAll();
    }

    public Exam addNewExam(Exam exam) {
        return examRepo.save(exam);
    }

    public Exam updateExam(Exam exam) {
        return examRepo.save(exam);
    }

    public void deleteExamById(String id) {
        examRepo.deleteById(id);
    }

    public Exam findExamBySubject(Subject subject) {
        return examRepo.findExamBySubject(subject)
                .orElseThrow(() -> new NotFoundException("Exam by subject " + subject + " was no found"));
    }

    public Exam findExamByExamTerm(String term) {
        return examRepo.findAllByExamTerm(term)
                .orElseThrow(() -> new NotFoundException("Exam by id " + term + " was no found"));
    }

    public Exam findExamById(String id) {
        return examRepo.findExamById(id)
                .orElseThrow(() -> new NotFoundException("Exam by id " + id + " was no found"));
    }

    public List<Exam> findALLExamBySubjectId(Subject subject) {
        return examRepo.findAllBySubject(subject);
    }
}
