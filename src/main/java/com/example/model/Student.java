package com.example.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;


@Entity
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

    public Student() {
    }

    public Student(String studentId, String studentName, String studentSurname, String studentTcNo,
                   String phoneNo, String studentEmail, String address, String studentImageURL) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.studentTcNo = studentTcNo;
        this.studentPhoneNo = phoneNo;
        this.studentEmail = studentEmail;
        this.studentAddress = address;
        this.studentImageURL = studentImageURL;

    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }

    public String getStudentTcNo() {
        return studentTcNo;
    }

    public void setStudentTcNo(String tcNo) {
        this.studentTcNo = tcNo;
    }

    public String getStudentPhoneNo() {
        return studentPhoneNo;
    }

    public void setStudentPhoneNo(String phoneNo) {
        this.studentPhoneNo = phoneNo;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String email) {
        this.studentEmail = email;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String address) {
        this.studentAddress = address;
    }

    public String getStudentImageURL() {
        return studentImageURL;
    }

    public void setStudentImageURL(String imageURL) {
        this.studentImageURL = imageURL;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentSurname='" + studentSurname + '\'' +
                ", tcNo=" + studentTcNo +
                ", phoneNo='" + studentPhoneNo + '\'' +
                ", email='" + studentEmail + '\'' +
                ", address='" + studentAddress + '\'' +
                ", imageURL='" + studentImageURL + '\'' +
                '}';
    }
}
