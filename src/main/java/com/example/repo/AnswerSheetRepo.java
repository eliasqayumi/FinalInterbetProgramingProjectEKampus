package com.example.repo;

import com.example.model.AnswerSheet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerSheetRepo extends JpaRepository<AnswerSheet,String> {
    void deleteAnswerSheetById(String id);
    Optional<AnswerSheet> findAnswerSheetById(String id);
}
