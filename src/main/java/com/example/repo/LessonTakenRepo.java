package com.example.repo;

import com.example.model.LessonTaken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LessonTakenRepo extends JpaRepository<LessonTaken, String> {
    Optional<LessonTaken> findLessonTakenById(String id);
    List<LessonTaken> findAllByStudent_StudentId(String studentId);
    List<LessonTaken> findAllBySubject_Id(String subjectId);
    void deleteById(String id);
}