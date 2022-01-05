package com.example.resource;


import com.example.model.Department;
import com.example.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/department")
public class DepartmentResource {
    private final DepartmentService departmentService;

    public DepartmentResource(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Department>> getAllDepartment() {
        List<Department> departments = departmentService.findAllDepartment();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") String id) {
        Department department = departmentService.findDepartmentById(id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
        Department department1 = departmentService.addDepartment(department);
        return new ResponseEntity<>(department1, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department department) {
        Department updateDepartment = departmentService.updateDepartment(department);
        return new ResponseEntity<>(updateDepartment, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable("id") String id) {
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
