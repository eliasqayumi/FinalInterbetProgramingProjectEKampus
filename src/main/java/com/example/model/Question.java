package com.example.model;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "questions")
@Data
public class Question {
    @Id
    @Column(name = "id", nullable = false, length = 10)
    private String id;

    @Column(name = "question", nullable = false, length = 250)
    private String question;

    @Column(name = "term", length = 10)
    private String term;

    @ManyToOne(optional = false)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    public Question() {
    }

    public Question(String id, String question, String term, Subject subject) {
        this.id = id;
        this.question = question;
        this.term = term;
        this.subject = subject;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}