package com.example.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id", nullable = false, length = 10)
    private String id;

    @Column(name = "user_full_name", nullable = false, length = 100)
    private String userFullName;

    @Column(name = "user_password", nullable = false, length = 45)
    private String userPassword;

    @Column(name = "user_role", nullable = false, length = 10)
    private String userRole;
    @Column(name = "user_email", nullable = false, length = 45)
    private String userEmail;

    public User() {
    }

    public User(String id, String userFullName, String userPassword, String userRole, String userEmail) {
        this.id = id;
        this.userFullName = userFullName;
        this.userPassword = userPassword;
        this.userRole = userRole;
        this.userEmail = userEmail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}