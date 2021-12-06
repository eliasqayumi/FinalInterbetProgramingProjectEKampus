package com.example.service;

import com.example.exception.NotFoundException;
import com.example.model.Answer;
import com.example.repo.AnswerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    private AnswerRepo answerRepo;

    @Autowired
    public AnswerService(AnswerRepo AnswerRepo) {
        this.answerRepo = AnswerRepo;
    }
    public List<Answer> findAllAnswer(){
        return answerRepo.findAll();
    }
    public Answer addAnswer(Answer Answer){
        return answerRepo.save(Answer);
    }
    public Answer updateAnswer(Answer Answer){
        return answerRepo.save(Answer);
    }
    public void deleteAnswer(String id) {
        answerRepo.deleteAnswerById(id);
    }

    public Answer findAnswerById(String id) {
        return answerRepo.findAnswerById(id)
                .orElseThrow(() -> new NotFoundException("User by id " + id + " was no found"));
    }

}
