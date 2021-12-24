package com.example.repo;

import com.example.model.AnswerSheet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnswerSheetRepo extends JpaRepository<AnswerSheet,String> {
    void deleteAnswerSheetById(String id);
    Optional<AnswerSheet> findAnswerSheetById(String id);
    List<AnswerSheet> findAllById_StudentId(String id);
    Optional<AnswerSheet> findAnswerSheetById_StudentIdAndId_QuestionId(String studentId,String questionId);
    List<AnswerSheet> findAllById_QuestionId(String id);
}
