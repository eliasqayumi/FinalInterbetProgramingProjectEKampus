package com.example.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class ScoreTableId implements Serializable {
    private static final long serialVersionUID = 5782903777518541471L;
    @Column(name = "student_id", nullable = false, length = 20)
    private String studentId;
    @Column(name = "subject_id", nullable = false, length = 20)
    private String subjectId;
    @Column(name = "exam_name", nullable = false, length = 20)
    private String examName;
    @Column(name = "exam_date", nullable = false)
    private LocalDate examDate;

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, examDate, examName, subjectId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ScoreTableId entity = (ScoreTableId) o;
        return Objects.equals(this.studentId, entity.studentId) &&
                Objects.equals(this.examDate, entity.examDate) &&
                Objects.equals(this.examName, entity.examName) &&
                Objects.equals(this.subjectId, entity.subjectId);
    }
}