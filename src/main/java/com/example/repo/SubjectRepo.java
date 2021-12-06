package com.example.repo;

import com.example.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepo extends JpaRepository<Subject,Long> {
    void deleteSubjectById(String id);
    Optional<Subject> findSubjectById(String id);
}
