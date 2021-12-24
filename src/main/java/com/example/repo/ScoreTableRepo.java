package com.example.repo;

import com.example.model.Answer;
import com.example.model.ScoreTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScoreTableRepo extends JpaRepository<ScoreTable,String> {
    void deleteScoreTableById(String id);
    Optional<ScoreTable> findScoreTableById(String id);
    List<ScoreTable> findAllById_StudentId(String id);
}
