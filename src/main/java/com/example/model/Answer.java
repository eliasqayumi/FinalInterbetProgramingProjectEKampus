package com.example.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "answers")
public class Answer {
    @Id
    @Column(name = "id", nullable = false, length = 10)

    private String id;

    @Column(name = "answer", nullable = false, length = 250)
    private String answer;

    @Column(name = "status", length = 10)
    private String status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    public Answer() {
    }

    public Answer(String id, String answer, String status, Question question) {
        this.id = id;
        this.answer = answer;
        this.status = status;
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id='" + id + '\'' +
                ", answer='" + answer + '\'' +
                ", status='" + status + '\'' +
                ", question=" + question +
                '}';
    }
}