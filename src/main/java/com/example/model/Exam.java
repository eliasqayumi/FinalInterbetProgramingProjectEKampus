package com.example.model;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "exam")
public class Exam {
    @Id
    @Column(name = "id", nullable = false, length = 20)
    private String id;

    @Column(name = "exam_term", nullable = false, length = 10)
    private String examTerm;

    @ManyToOne(optional = false)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @Column(name = "exam_date_time", nullable = false)
    private Instant examDateTime;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Instant getExamDateTime() {
        return examDateTime;
    }

    public void setExamDateTime(Instant examDateTime) {
        this.examDateTime = examDateTime;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getExamTerm() {
        return examTerm;
    }

    public void setExamTerm(String examTerm) {
        this.examTerm = examTerm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}