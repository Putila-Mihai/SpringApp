package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Value("${app.title}")
    private String appName;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String showUserList(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("appName", appName);
        return "index";
    }
}
