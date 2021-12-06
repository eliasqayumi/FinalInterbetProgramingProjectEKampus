package com.example.repo;

import com.example.model.Answer;
import com.example.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionRepo extends JpaRepository<Question,String> {
    void deleteQuestionById(String id);
    Optional<Question> findQuestionById(String id);
}
