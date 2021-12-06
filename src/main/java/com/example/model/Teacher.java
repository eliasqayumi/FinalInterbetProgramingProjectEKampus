package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @Column(name = "teacher_id", nullable = false,length = 45)
    private String id;

    @Column(name = "teacher_name", nullable = false, length = 45)
    private String teacherName;

    @Column(name = "teacher_surname", nullable = false, length = 45)
    private String teacherSurname;

    @Column(name = "teacher_tcno", nullable = false, length = 11)
    private String teacherTcno;

    @Column(name = "teacher_phone", nullable = false, length = 11)
    private String teacherPhone;

    @Column(name = "teacher_email", nullable = false, length = 45)
    private String teacherEmail;

    @Column(name = "teacher_address", nullable = false, length = 200)
    private String teacherAddress;

    public Teacher() {
    }

    public Teacher(String id, String teacherName, String teacherSurname, String teacherTcno, String teacherPhone, String teacherEmail, String teacherAddress) {
        this.id = id;
        this.teacherName = teacherName;
        this.teacherSurname = teacherSurname;
        this.teacherTcno = teacherTcno;
        this.teacherPhone = teacherPhone;
        this.teacherEmail = teacherEmail;
        this.teacherAddress = teacherAddress;
    }

    public String getTeacherAddress() {
        return teacherAddress;
    }

    public void setTeacherAddress(String teacherAddress) {
        this.teacherAddress = teacherAddress;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone;
    }

    public String getTeacherTcno() {
        return teacherTcno;
    }

    public void setTeacherTcno(String teacherTcno) {
        this.teacherTcno = teacherTcno;
    }

    public String getTeacherSurname() {
        return teacherSurname;
    }

    public void setTeacherSurname(String teacherSurname) {
        this.teacherSurname = teacherSurname;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", teacherName='" + teacherName + '\'' +
                ", teacherSurname='" + teacherSurname + '\'' +
                ", teacherTcno='" + teacherTcno + '\'' +
                ", teacherPhone='" + teacherPhone + '\'' +
                ", teacherEmail='" + teacherEmail + '\'' +
                ", teacherAddress='" + teacherAddress + '\'' +
                '}';
    }
}