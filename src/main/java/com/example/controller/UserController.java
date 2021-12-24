package com.example.controller;

import com.example.model.User;
import com.example.service.StudentService;
import com.example.service.TeacherService;
import com.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    private UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;

    }

    @RequestMapping("/user")
    public String getAllUser(HttpServletRequest request, Model model) {
        if (getString(request)) return "redirect:/";
        List<User> users = userService.finAllUsers();
        if (users != null)
            model.addAttribute("users", users);
        return "admin/panel/panelUser";
    }

    @RequestMapping("/user/add")
    public String addNewUser(@ModelAttribute("user") User user, HttpServletRequest request) {
        if (getString(request)) return "redirect:/";
        String userRole = request.getParameter("userRole");
        if (user.getUserFullName() != null && user.getUserPassword() != null
                && user.getUserEmail() != null && userRole != null) {
            long count= userService.finAllUsers().size();
            String userId= "USID"+(++count);
            User newUser = new User(userId,user.getUserFullName(), user.getUserPassword(), userRole, user.getUserEmail());
            userService.addUser(newUser);
            return "redirect:/user";
        }
        return "admin/add/addNewUser";
    }
    @RequestMapping("/user/update/{id}")
    public String updateUser(@PathVariable String id, @ModelAttribute("user") User user, HttpServletRequest request, Model model) {
        if (getString(request)) return "redirect:/";
        user = userService.findUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("admin", "Admin");
        model.addAttribute("teacher", "Teacher");
        model.addAttribute("student", "Student");
        return "admin/update/updateUser";
    }

    @RequestMapping("/user/{id}")
    public String update(@PathVariable String id, @ModelAttribute("user") @Valid User user, HttpServletRequest request) {
        if (getString(request)) return "redirect:/";
        User updateUser = userService.findUserById(id);
        if (user.getId() != null && user.getUserFullName() != null && user.getUserPassword() != null
                && user.getUserEmail() != null && user.getUserRole() != null) {
            updateUser.setUserFullName(user.getUserFullName());
            updateUser.setUserPassword(user.getUserPassword());
            updateUser.setUserRole(user.getUserRole());
            updateUser.setId(id);
            updateUser.setUserEmail(user.getUserEmail());
            userService.updateUser(updateUser);
            return "redirect:/user";
        }
        return "admin/update/updateUser";
    }

//    deleting the userid
    @RequestMapping("/user/delete/{id}")
    public String delete(@PathVariable String id) {
        try {
            userService.deleteUser(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/user";
    }

    private boolean getString(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getUserRole().equalsIgnoreCase("admin"))
            return true;
        return false;
    }

}
