package com.example.auctionhouse_webapplication.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.auctionhouse_webapplication.dto.RegistrationDto;
import com.example.auctionhouse_webapplication.dto.RegistrationDtoWithRoles;
import com.example.auctionhouse_webapplication.service.UserService;

@Controller
public class RegisterController {

    private UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/register/admin")
    public String showRegisterAdminForm(Model model) {
        model.addAttribute("registration", new RegistrationDtoWithRoles());
        return "register_admin";

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/register/admin")
    public String registerAdmin(@Valid RegistrationDtoWithRoles registrationDto, BindingResult bindingResult, Model model) {
        userService.createWithAdminPrivileges(registrationDto);
        return "success";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registration", new RegistrationDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid RegistrationDto registrationDto, BindingResult bindingResult, Model model) {
        userService.createBuyer(registrationDto);
        return "success";
    }

}