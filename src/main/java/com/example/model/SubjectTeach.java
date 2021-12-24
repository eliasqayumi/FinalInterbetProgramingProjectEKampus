package com.example.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "subject_teach")
public class SubjectTeach {
    @EmbeddedId
    private SubjectTeachId id;

    public SubjectTeachId getId() {
        return id;
    }

    public void setId(SubjectTeachId id) {
        this.id = id;
    }
}