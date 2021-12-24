package com.example.service;

import com.example.exception.NotFoundException;
import com.example.model.Question;
import com.example.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private QuestionRepo questionRepo;

    @Autowired
    public QuestionService(QuestionRepo QuestionRepo) {
        this.questionRepo = QuestionRepo;
    }

    public List<Question> findAllQuestion() {
        return questionRepo.findAll();
    }

    public Question addQuestion(Question Question) {
        return questionRepo.save(Question);
    }

    public Question updateQuestion(Question Question) {
        return questionRepo.save(Question);
    }

    public void deleteQuestion(String id) {
        questionRepo.deleteQuestionById(id);
    }

    public Question findQuestionById(String id) {
        return questionRepo.findQuestionById(id)
                .orElseThrow(() -> new NotFoundException("User by id " + id + " was no found"));
    }

    public List<Question> findAllQuestionBySubjectID(String id) {
        return questionRepo.findAllBySubject_Id(id);
    }

    public List<Question> findAllBySubjectIdAndTerm(String subjectId, String term) {
        return questionRepo.findAllBySubject_IdAndTerm(subjectId, term);
    }
}
