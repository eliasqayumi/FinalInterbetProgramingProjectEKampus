package com.example.repo;

import com.example.model.Answer;
import com.example.model.LessonTaken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LessonTakenRepo extends JpaRepository<LessonTaken,String> {
    void deleteLessonTakenById(String id);
    Optional<LessonTaken> findLessonTakenById(String id);
}
