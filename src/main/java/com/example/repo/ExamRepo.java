package com.example.repo;

import com.example.model.Exam;
import com.example.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ExamRepo extends JpaRepository<Exam, String> {
    List<Exam> findAllByExamDateTime(LocalDateTime localDateTime);

    List<Exam> findAllBySubject(Subject subject);

    Optional<Exam> findExamBySubject(Subject subject);

    Optional<Exam> findAllByExamTerm(String term);

    Optional<Exam> findExamById(String id);

    void deleteById(String id);

}
