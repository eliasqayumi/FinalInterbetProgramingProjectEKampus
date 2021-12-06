package com.example.repo;

import com.example.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerRepo extends JpaRepository<Answer,String> {
    void deleteAnswerById(String id);
    Optional<Answer> findAnswerById(String id);
}
