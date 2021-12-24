package com.example.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "department")
public class Department {
    @Id
    @Column(name = "department_id", nullable = false, length = 10)
    private String id;

    @Column(name = "department_name", nullable = false, length = 100)
    private String departmentName;

    public Department() {
    }

    public Department(String id, String departmentName) {
        this.id = id;
        this.departmentName = departmentName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}