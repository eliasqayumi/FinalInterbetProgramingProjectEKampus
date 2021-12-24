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

    public List<LessonTaken> findAllLessonTaken() {
        return lessonTakenRepo.findAll();
    }

    public LessonTaken addLessonTaken(LessonTaken LessonTaken) {
        return lessonTakenRepo.save(LessonTaken);
    }

    public LessonTaken updateLessonTaken(LessonTaken LessonTaken) {
        return lessonTakenRepo.save(LessonTaken);
    }

    public void deleteLessonTakenId(String id) {
        lessonTakenRepo.deleteById(id);
    }

    public LessonTaken findLessonTakenById(String id) {
        return lessonTakenRepo.findLessonTakenById(id)
                .orElseThrow(() -> new NotFoundException("User by id " + id + " was no found"));
    }
    public List<LessonTaken> findAllBySubjectId(String id){
        return lessonTakenRepo.findAllBySubject_Id(id);
    }

    public List<LessonTaken> findAllLessonTakenByStudentId(String id) {
        return lessonTakenRepo.findAllByStudent_StudentId(id);
    }


}
