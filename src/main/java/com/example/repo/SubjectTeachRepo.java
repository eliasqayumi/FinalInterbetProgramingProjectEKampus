package com.example.repo;

import com.example.model.SubjectTeach;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectTeachRepo extends JpaRepository<SubjectTeach,String> {
    void deleteSubjectTeachById(String id);
    Optional<SubjectTeach> findSubjectTeachById_Id(String id);
    Optional<SubjectTeach> findSubjectTeachById_TeacherId(String id);
    List<SubjectTeach> findAllById_TeacherId(String id);
}
