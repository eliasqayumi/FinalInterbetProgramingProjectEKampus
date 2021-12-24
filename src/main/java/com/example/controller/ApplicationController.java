package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller

public class ApplicationController {
    private UserService userService;
    private String message = null;

    public ApplicationController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String home(Model model, HttpServletRequest request) {
        request.getSession().invalidate();
        String username = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies)
                if (cookie.getName().equals("user"))
                    username = cookie.getValue();
        model.addAttribute("username", username);
        if (message != null) {
            model.addAttribute("message", message);
            message = null;
        }
        return "index";
    }

    @RequestMapping("/login")
    public String login(@ModelAttribute("user") User user, Model model, HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            user = userService.findUserByUserEmail(username);


            Cookie[] cookies = request.getCookies();
            if (cookies != null)
                for (Cookie cookie : cookies)
                    if (!cookie.getName().equals("user")) {
                        Cookie newCookie = new Cookie("user", username);
                        newCookie.setMaxAge(60 * 60 * 24);
                        response.addCookie(newCookie);
                    }

            if (user.getUserPassword().equals(password)) {
                request.getSession().invalidate();
                HttpSession newSession = request.getSession();
                newSession.setMaxInactiveInterval(3600);
                String encode = response.encodeURL(request.getContextPath());
                model.addAttribute("user", user);
                newSession.setAttribute("user", user);
                return "redirect:" + encode + user.getUserRole().toLowerCase() + "s";
            }
        } catch (RuntimeException e) {
            this.message = e.getMessage();
        }
        return "redirect:/";
    }

    @RequestMapping("/admins")
    public String adminPanel(@ModelAttribute("user") User user, HttpServletRequest request) {
        User user1 = (User) request.getSession().getAttribute("user");
        if (user1 == null || !user1.getUserRole().equalsIgnoreCase("admin"))
            return "redirect:/";
        return "admin/add/admin";
    }

    @RequestMapping("/teachers")
    public String teacherPanel(@ModelAttribute("user") User user, Model model, HttpServletRequest request) {
        User user1 = (User) request.getSession().getAttribute("user");
        if (user1 == null || !user1.getUserRole().equalsIgnoreCase("teacher"))
            return "redirect:/";
        return "teacher/teacher";
    }

    @RequestMapping("/students")
    public String studentPanel(@ModelAttribute("user") User user, HttpServletRequest request) {
        User user1 = (User) request.getSession().getAttribute("user");
        if (user1 == null || !user1.getUserRole().equalsIgnoreCase("student"))
            return "redirect:/";
        return "student/student";
    }
}
