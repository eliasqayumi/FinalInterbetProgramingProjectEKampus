package com.example.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AnswerSheetId implements Serializable {
    private static final long serialVersionUID = -5601130076784058468L;
    @Column(name = "student_id", nullable = false, length = 20)
    private String studentId;
    @Column(name = "question_id", nullable = false, length = 20)
    private String questionId;
    @Column(name = "answer_id", nullable = false, length = 20)
    private String answerId;

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, answerId, questionId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AnswerSheetId entity = (AnswerSheetId) o;
        return Objects.equals(this.studentId, entity.studentId) &&
                Objects.equals(this.answerId, entity.answerId) &&
                Objects.equals(this.questionId, entity.questionId);
    }

    @Override
    public String toString() {
        return "AnswerSheetId{" +
                "studentId='" + studentId + '\'' +
                ", questionId='" + questionId + '\'' +
                ", answerId='" + answerId + '\'' +
                '}';
    }
}