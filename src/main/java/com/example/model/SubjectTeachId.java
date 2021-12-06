package com.example.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class SubjectTeachId implements Serializable {
    private static final long serialVersionUID = 148625094439424046L;
    @Column(name = "subject_id", nullable = false, length = 20)
    private String subjectId;
    @Column(name = "teacher_id", nullable = false, length = 20)
    private String teacherId;
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    @Column(name = "finish_date", nullable = false)
    private LocalDate finishDate;

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, finishDate, subjectId, startDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SubjectTeachId entity = (SubjectTeachId) o;
        return Objects.equals(this.teacherId, entity.teacherId) &&
                Objects.equals(this.finishDate, entity.finishDate) &&
                Objects.equals(this.subjectId, entity.subjectId) &&
                Objects.equals(this.startDate, entity.startDate);
    }
}