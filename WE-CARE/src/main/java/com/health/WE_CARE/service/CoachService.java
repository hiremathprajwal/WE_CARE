package com.health.WE_CARE.service;

import com.health.WE_CARE.dto.CoachDTO;
import com.health.WE_CARE.dto.LoginDTO;
import com.health.WE_CARE.entity.BookingEntity;
import com.health.WE_CARE.entity.CoachEntity;
import com.health.WE_CARE.exception.ExceptionConstants;
import com.health.WE_CARE.exception.WecareException;
import com.health.WE_CARE.repository.BookRepository;
import com.health.WE_CARE.repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoachService {

    @Autowired
    private CoachRepository coachRepository;

    public String createCoach(CoachDTO coachDTO) {
        CoachEntity entity = new CoachEntity();
        entity.setName(coachDTO.getName());
        entity.setPassword(coachDTO.getPassword());
        entity.setGender(coachDTO.getGender());
        entity.setDateOfBirth(coachDTO.getDateOfBirth());
        entity.setMobileNumber(coachDTO.getMobileNumber());
        entity.setSpeciality(coachDTO.getSpeciality());

        CoachEntity savedEntity = coachRepository.save(entity);

        return "Coach created with ID: " + savedEntity.getCoachId();
    }


    public boolean loginCoach(LoginDTO loginDTO) {
        Optional<CoachEntity> optionalCoach = coachRepository.findByCoachId(loginDTO.getId());
        return optionalCoach.map(coach -> coach.getPassword().equals(loginDTO.getPassword())).orElse(false);
    }

    public CoachDTO getCoachProfile(String coachId) {
        CoachEntity entity = coachRepository.findByCoachId(coachId)
                .orElseThrow(() -> new WecareException(ExceptionConstants.COACH_NOT_FOUND.toString()));

        CoachDTO dto = new CoachDTO();
        dto.setCoachId(entity.getCoachId());
        dto.setName(entity.getName());
        dto.setPassword(entity.getPassword());
        dto.setGender(entity.getGender());
        dto.setDateOfBirth(entity.getDateOfBirth());
        dto.setMobileNumber(entity.getMobileNumber());
        dto.setSpeciality(entity.getSpeciality());

        return dto;
    }

    public List<CoachDTO> showAllCoaches() {
        return coachRepository.findAll().stream().map(entity -> {
            CoachDTO dto = new CoachDTO();
            dto.setCoachId(entity.getCoachId());
            dto.setName(entity.getName());
            dto.setPassword(entity.getPassword());
            dto.setGender(entity.getGender());
            dto.setDateOfBirth(entity.getDateOfBirth());
            dto.setMobileNumber(entity.getMobileNumber());
            dto.setSpeciality(entity.getSpeciality());
            return dto;
        }).collect(Collectors.toList());
    }


}
