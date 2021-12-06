package com.example.service;

import com.example.exception.NotFoundException;
import com.example.model.ScoreTable;
import com.example.repo.ScoreTableRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreTableService {
    private ScoreTableRepo ScoreTableRepo;

    @Autowired
    public ScoreTableService(ScoreTableRepo ScoreTableRepo) {
        this.ScoreTableRepo = ScoreTableRepo;
    }
    public List<ScoreTable> findAllScoreTable(){
        return ScoreTableRepo.findAll();
    }
    public ScoreTable addScoreTable(ScoreTable ScoreTable){
        return ScoreTableRepo.save(ScoreTable);
    }
    public ScoreTable updateScoreTable(ScoreTable ScoreTable){
        return ScoreTableRepo.save(ScoreTable);
    }
    public void deleteScoreTable(String id) {
        ScoreTableRepo.deleteScoreTableById(id);
    }

    public ScoreTable findScoreTableById(String id) {
        return ScoreTableRepo.findScoreTableById(id)
                .orElseThrow(() -> new NotFoundException("User by id " + id + " was no found"));
    }

}
