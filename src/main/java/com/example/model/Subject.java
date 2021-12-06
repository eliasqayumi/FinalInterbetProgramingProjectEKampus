package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @Column(name = "subject_id", nullable = false, length = 50)
    private String id;

    @Column(name = "subject_name", nullable = false, length = 45)
    private String subjectName;

    @Column(name = "subject_credit", nullable = false, length = 45)
    private String subjectCredit;

    public String getSubjectCredit() {
        return subjectCredit;
    }

    public void setSubjectCredit(String subjectCredit) {
        this.subjectCredit = subjectCredit;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id='" + id + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", subjectCredit='" + subjectCredit + '\'' +
                '}';
    }
}