package com.example.resource;

import com.example.model.ScoreTable;
import com.example.service.ScoreTableService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/scoreTable")
public class ScoreTableResource {
    private final ScoreTableService scoreTableService;

    public ScoreTableResource(ScoreTableService scoreTableService) {
        this.scoreTableService = scoreTableService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ScoreTable>> getAllScoreTable() {
        List<ScoreTable> scoreTables = scoreTableService.findAllScoreTable();
        return new ResponseEntity<>(scoreTables, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ScoreTable> getScoreTableById(@PathVariable("id") String id) {
        ScoreTable scoreTable = scoreTableService.findScoreTableById(id);
        return new ResponseEntity<>(scoreTable, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ScoreTable> addScoreTable(@RequestBody ScoreTable scoreTable) {
        ScoreTable scoreTable1 = scoreTableService.addScoreTable(scoreTable);
        return new ResponseEntity<>(scoreTable1, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<ScoreTable> updateScoreTable(@RequestBody ScoreTable scoreTable) {
        ScoreTable updateScoreTable = scoreTableService.updateScoreTable(scoreTable);
        return new ResponseEntity<>(updateScoreTable, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteScoreTable(@PathVariable("id") String id) {
        scoreTableService.deleteScoreTable(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
