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

    @Column(name = "inrole_date")
    private LocalDate inroleDate;

    public LocalDate getInroleDate() {
        return inroleDate;
    }

    public void setInroleDate(LocalDate inroleDate) {
        this.inroleDate = inroleDate;
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