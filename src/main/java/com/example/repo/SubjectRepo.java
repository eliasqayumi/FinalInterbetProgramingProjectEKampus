package com.example.repo;

import com.example.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepo extends JpaRepository<Subject,String> {
    Optional<Subject> findSubjectById(String id);

}
