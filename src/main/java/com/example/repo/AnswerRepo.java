package com.example.repo;

import com.example.model.Answer;
import com.example.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnswerRepo extends JpaRepository<Answer,String> {
    void deleteAnswerById(String id);
    Optional<Answer> findAnswerById(String id);
    List<Answer> findAllAnswerByQuestion(Question question);

}
