package com.example.model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "answer_sheet")
public class AnswerSheet {
    @EmbeddedId
    private AnswerSheetId id;

    public AnswerSheetId getId() {
        return id;
    }

    public void setId(AnswerSheetId id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AnswerSheet{" +
                "id=" + id +
                '}';
    }
}