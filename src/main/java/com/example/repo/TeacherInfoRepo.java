package com.example.repo;

import com.example.model.Subject;
import com.example.model.TeacherInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherInfoRepo extends JpaRepository<TeacherInfo,String> {
    void deleteTeacherInfoById(String id);
    Optional<TeacherInfo> findTeacherInfoById(String id);
}
