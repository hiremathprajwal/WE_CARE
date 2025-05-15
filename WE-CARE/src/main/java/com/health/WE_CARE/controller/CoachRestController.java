package com.health.WE_CARE.controller;

import com.health.WE_CARE.dto.CoachDTO;
import com.health.WE_CARE.dto.LoginDTO;
import com.health.WE_CARE.service.CoachService;
import com.health.WE_CARE.dto.BookingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coaches")
public class CoachRestController {

    @Autowired
    private CoachService coachService;

    @PostMapping
    public String signUp(@RequestBody CoachDTO coachDTO) {
        return coachService.createCoach(coachDTO);
    }

    @PostMapping("/login")
    public boolean login(@RequestBody LoginDTO loginDTO) {
        return coachService.loginCoach(loginDTO);
    }

    @GetMapping("/{coachId}")
    public CoachDTO getCoachProfile(@PathVariable String coachId) {
        return coachService.getCoachProfile(coachId);
    }



    @GetMapping("/all")
    public List<CoachDTO> getAllCoaches() {
        return coachService.showAllCoaches();
    }
}
