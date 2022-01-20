package com.example.repo;

import com.example.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepo extends JpaRepository<Department,String> {
    void deleteDepartmentById(String id);
    Optional<Department> findDepartmentById(String id);
    Optional<Department> findDepartmentByDepartmentName(String departmentName);
}
