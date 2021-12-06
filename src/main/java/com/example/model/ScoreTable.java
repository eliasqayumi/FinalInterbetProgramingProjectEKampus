package com.example.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "score_table")
public class ScoreTable {
    @EmbeddedId
    private ScoreTableId id;

    @Column(name = "score_id", nullable = false, length = 10)
    private String scoreId;

    @Column(name = "marks", nullable = false)
    private Integer marks;

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    public String getScoreId() {
        return scoreId;
    }

    public void setScoreId(String scoreId) {
        this.scoreId = scoreId;
    }

    public ScoreTableId getId() {
        return id;
    }

    public void setId(ScoreTableId id) {
        this.id = id;
    }
}