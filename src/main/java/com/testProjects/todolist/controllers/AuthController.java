package com.testProjects.todolist.controllers;

import com.testProjects.todolist.models.User;
import com.testProjects.todolist.services.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    private final UserServiceImpl userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("user") User user, @RequestParam("confirmPassword") String confirmPassword, Model model) {
        // Check if passwords match
        if (!user.getPassword().equals(confirmPassword)) {
            // Redirect with error for password mismatch
            return "redirect:/signup?error=passwordMismatch";
        }

        // Check if username is available (not already taken)
        if (userService.isUsernameTaken(user.getUsername())) {
            // Redirect with error for username already taken
            return "redirect:/signup?error=usernameTaken";
        }

        // Hash the password and save the user
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userService.saveUser(user);

        // Redirect to login page after successful signup
        return "redirect:/signin";
    }

    @GetMapping("/signup")
    public String register(@RequestParam(value = "error", required = false) String error, Model model) {
        model.addAttribute("title", "REGISTER");

        // Set user-friendly error messages based on the error parameter
        if ("passwordMismatch".equals(error)) {
            model.addAttribute("error", "Passwords do not match.");
        } else if ("usernameTaken".equals(error)) {
            model.addAttribute("error", "Username is already taken.");
        }

        return "signup"; // Return the signup page
    }

    @GetMapping("/signin")
    public String showLoginForm() {
        return "login"; // This will return the login.html Thymeleaf template
    }
}
