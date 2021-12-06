package com.example.repo;

import com.example.model.Subject;
import com.example.model.SubjectTeach;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectTeachRepo extends JpaRepository<SubjectTeach,String> {
    void deleteSubjectTeachById(String id);
    Optional<SubjectTeach> findSubjectTeachById(String id);
}
