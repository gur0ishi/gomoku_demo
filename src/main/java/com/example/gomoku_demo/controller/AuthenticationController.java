package com.example.gomoku_demo.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.gomoku_demo.model.User;
import com.example.gomoku_demo.service.UserService;

@Controller
public class AuthenticationController {

    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping(value = {"/", "/home"})
    public String homepage() {
        return "home";
    }

    @GetMapping("/login")
    public String login1() {
        return "login";
    }

    @GetMapping("/register")
    public String index(Model model) {
        System.out.println("re-zz");
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute @Valid User user, BindingResult bindingResult) {
        System.out.println(user.getPassword() + "1");
        if (bindingResult.hasErrors()) {
            return "register";
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            System.out.println("|| " + user.getUsername() + " || " + user.getPassword());
            return Optional.ofNullable(userService.register(user)).map(u -> "redirect:/login").orElse("home");
        }
    }
}
