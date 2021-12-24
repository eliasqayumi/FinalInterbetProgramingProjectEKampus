package com.example.repo;

import com.example.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepo extends JpaRepository<Question,String> {
    void deleteQuestionById(String id);
    Optional<Question> findQuestionById(String id);
    List<Question> findAllBySubject_Id(String id);
    List<Question> findAllBySubject_IdAndTerm(String id,String term);
}
