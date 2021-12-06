package com.example.service;

import com.example.exception.NotFoundException;
import com.example.model.LessonTaken;
import com.example.repo.LessonTakenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonTakenService {
    private LessonTakenRepo lessonTakenRepo;

    @Autowired
    public LessonTakenService(LessonTakenRepo LessonTakenRepo) {
        this.lessonTakenRepo = LessonTakenRepo;
    }
    public List<LessonTaken> findAllLessonTaken(){
        return lessonTakenRepo.findAll();
    }
    public LessonTaken addLessonTaken(LessonTaken LessonTaken){
        return lessonTakenRepo.save(LessonTaken);
    }
    public LessonTaken updateLessonTaken(LessonTaken LessonTaken){
        return lessonTakenRepo.save(LessonTaken);
    }
    public void deleteLessonTaken(String id) {
        lessonTakenRepo.deleteLessonTakenById(id);
    }

    public LessonTaken findLessonTakenById(String id) {
        return lessonTakenRepo.findLessonTakenById(id)
                .orElseThrow(() -> new NotFoundException("User by id " + id + " was no found"));
    }

}
