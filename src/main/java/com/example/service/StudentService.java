package com.example.service;

import com.example.exception.NotFoundException;
import com.example.model.Student;
import com.example.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }


    public Student addStudent(Student student) {
        return studentRepo.save(student);
    }

    public List<Student> findAllStudents() {
        return studentRepo.findAll();
    }

    public Student updateStudent(Student student) {
        return studentRepo.save(student);
    }

    public Student findStudentById(String id) {
        return studentRepo.findStudentByStudentId(id)
                .orElseThrow(() -> new NotFoundException("Student by id " + id + " not found"));
    }
    public Student findStudentByStudentEmail(String email){
        return studentRepo.findStudentByStudentEmail(email).orElseThrow(()-> new NotFoundException("Student by Email "+email+" not found"));
    }
    public void deleteStudent(String id) {
        studentRepo.deleteById(id);
    }

}
