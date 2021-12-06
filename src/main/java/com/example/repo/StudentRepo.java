package com.example.repo;

import com.example.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StudentRepo extends JpaRepository<Student,String> {

    void deleteStudentByStudentId(String id);
    Optional<Student> findStudentByStudentId(String id);

}
