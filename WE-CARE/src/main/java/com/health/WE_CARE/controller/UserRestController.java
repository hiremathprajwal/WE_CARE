package com.health.WE_CARE.controller;

import com.health.WE_CARE.dto.BookingDTO;
import com.health.WE_CARE.dto.LoginDTO;
import com.health.WE_CARE.dto.UserDTO;
import com.health.WE_CARE.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    // User signup
    @PostMapping
    public String signUp(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    // User login
    @PostMapping("/login")
    public boolean login(@RequestBody LoginDTO loginDTO) {
        return userService.loginUser(loginDTO);
    }

    // View user profile
    @GetMapping("/{userId}")
    public UserDTO getUserProfile(@PathVariable String userId) {
        return userService.getUserProfile(userId);
    }

    // View user's upcoming appointments
    @GetMapping("/booking/{userId}")
    public List<BookingDTO> getUserBookings(@PathVariable String userId) {
        return userService.getUserBookings(userId);
    }
}
