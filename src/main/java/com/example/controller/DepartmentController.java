package com.example.controller;

import com.example.model.Department;
import com.example.model.User;
import com.example.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class DepartmentController {
    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    //    Department resource
    @RequestMapping("/department")
    public String getAllDepartment(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getUserRole().equalsIgnoreCase("admin"))
            return "redirect:/";
        List<Department> departments = departmentService.findAllDepartment();
        if (departments != null)
            model.addAttribute("departments", departments);
        return "admin/panel/panelDepartment";
    }

    @RequestMapping("/department/add")
    public String addNewDepartment(@ModelAttribute("department") Department department, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getUserRole().equalsIgnoreCase("admin"))
            return "redirect:/";
        long count = departmentService.findAllDepartment().size();
        String id = "CUM" + (++count);
        if (department.getDepartmentName() != null) {
            Department newDepartment = new Department(id, department.getDepartmentName());
            departmentService.addDepartment(newDepartment);
            return "redirect:/department";
        }
        return "admin/add/addNewDepartment";
    }

    @RequestMapping("/department/edit/{id}")
    public String updateDepartment(@PathVariable String id, @ModelAttribute("department") Department Department, HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getUserRole().equalsIgnoreCase("admin"))
//            return "redirect:/";
        Department = departmentService.findDepartmentById(id);
        model.addAttribute("department", Department);
        return "admin/update/updateDepartment";
    }

    @RequestMapping("/department/update/{id}")
    public String update(@PathVariable String id, @ModelAttribute("department") @Valid Department department, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getUserRole().equalsIgnoreCase("admin"))
            return "redirect:/";
        Department updateDepartment = departmentService.findDepartmentById(id);
        if (department.getDepartmentName() != null) {
            updateDepartment.setId(id);
            updateDepartment.setDepartmentName(department.getDepartmentName());
            departmentService.updateDepartment(updateDepartment);
            return "redirect:/department";
        }
        return "admin/update/updateDepartment";
    }

}
