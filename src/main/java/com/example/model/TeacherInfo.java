package com.example.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "teacher_info")
public class TeacherInfo {
    @Id
    @Column(name = "teacher_id", nullable = false, length = 20)
    private String id;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "enrol_date")
    private LocalDate enrolDate;

    public LocalDate getEnrolDate() {
        return enrolDate;
    }

    public void setEnrolDate(LocalDate enrolDate) {
        this.enrolDate = enrolDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}