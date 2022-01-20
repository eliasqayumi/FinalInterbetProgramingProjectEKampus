package com.example.repo;

import com.example.model.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentInfoRepo extends JpaRepository<StudentInfo,String> {
    void deleteStudentInfoById(String id);
    Optional<StudentInfo> findStudentInfoById(String id);
}
