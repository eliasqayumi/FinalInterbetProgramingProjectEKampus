package com.example.service;

import com.example.exception.NotFoundException;
import com.example.model.Department;
import com.example.repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    private DepartmentRepo departmentRepo;
    public DepartmentService(DepartmentRepo DepartmentRepo) {
        this.departmentRepo = DepartmentRepo;
    }

    public List<Department> findAllDepartment() {
        return departmentRepo.findAll();
    }

    public Department addDepartment(Department Department) {
        return departmentRepo.save(Department);
    }

    public Department updateDepartment(Department Department) {
        return departmentRepo.save(Department);
    }

    public void deleteDepartment(String id) {
        departmentRepo.deleteById(id);
    }

    public Department findDepartmentById(String id) {
        return departmentRepo.findDepartmentById(id)
                .orElseThrow(() -> new NotFoundException("Department by id " + id + " was no found"));
    }
    public Department findDepartmentByDepartmentName(String departmentName) {
        return departmentRepo.findDepartmentByDepartmentName(departmentName)
                .orElseThrow(() -> new NotFoundException("Department by id " + departmentName + " was no found"));
    }

}
