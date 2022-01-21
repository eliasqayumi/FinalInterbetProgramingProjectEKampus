package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {

    @Id
    @Column(nullable = false, updatable = false)
    private String studentId;

    @Column(name = "student_name", nullable = false, columnDefinition = "TEXT")
    private String studentName;

    @Column(name = "student_surname", nullable = false, columnDefinition = "TEXT")
    private String studentSurname;

    @Column(name = "student_tc_no", nullable = false, updatable = false)
    private String studentTcNo;

    @Column(name = "student_phone_no", nullable = false, columnDefinition = "TEXT")
    private String studentPhoneNo;
    @Email(message = "Email is invalid")
    @Column(name = "student_email", nullable = false)
    private String studentEmail;

    @Column(name = "student_address", nullable = false, columnDefinition = "TEXT")
    private String studentAddress;

    @Column(name = "student_image_url", nullable = false, columnDefinition = "TEXT")
    private String studentImageURL;

}
