package com.example.repo;

import com.example.model.Student;
import com.example.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepo extends JpaRepository<Teacher,String> {

    void deleteTeacherById(String id);
    Optional<Teacher> findTeacherById(String id);
    Optional<Teacher> findTeacherByTeacherEmail(String email);
}
