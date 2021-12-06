package com.example.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lesson_taken")
public class LessonTaken {
    @EmbeddedId
    private LessonTakenId id;

    @Column(name = "id", nullable = false, length = 10)
    private String id1;

    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public LessonTakenId getId() {
        return id;
    }

    public void setId(LessonTakenId id) {
        this.id = id;
    }
}