package com.example.service;

import com.example.exception.NotFoundException;
import com.example.model.Answer;
import com.example.model.AnswerSheet;
import com.example.model.Student;
import com.example.repo.AnswerSheetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerSheetService {
    private AnswerSheetRepo answerSheetRepo;

    @Autowired
    public AnswerSheetService(AnswerSheetRepo answerSheetRepo) {
        this.answerSheetRepo = answerSheetRepo;
    }
    public List<AnswerSheet> findAllAnswerSheet(){
        return answerSheetRepo.findAll();
    }
    public AnswerSheet addAnswerSheet(AnswerSheet answerSheet){
        return answerSheetRepo.save(answerSheet);
    }
    public AnswerSheet updateAnswerSheet(AnswerSheet answerSheet){
        return answerSheetRepo.save(answerSheet);
    }
    public void deleteAnswerSheet(String id) {
        answerSheetRepo.deleteAnswerSheetById(id);
    }

    public AnswerSheet findAnswerSheetById(String id) {
        return answerSheetRepo.findAnswerSheetById(id)
                .orElseThrow(() -> new NotFoundException("User by id " + id + " was no found"));
    }

}
