package com.health.WE_CARE.service;

import com.health.WE_CARE.dto.BookingDTO;
import com.health.WE_CARE.dto.UserDTO;
import com.health.WE_CARE.dto.LoginDTO;
import com.health.WE_CARE.entity.BookingEntity;
import com.health.WE_CARE.entity.UserEntity;
import com.health.WE_CARE.exception.ExceptionConstants;
import com.health.WE_CARE.exception.WecareException;
import com.health.WE_CARE.repository.BookRepository;
import com.health.WE_CARE.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;


    public String createUser(UserDTO userDTO) {
        UserEntity entity = new UserEntity();
        entity.setName(userDTO.getName());
        entity.setPassword(userDTO.getPassword());
        entity.setGender(userDTO.getGender());
        entity.setDateOfBirth(userDTO.getDateOfBirth());
        entity.setMobileNumber(userDTO.getMobileNumber());
        entity.setEmail(userDTO.getEmail());
        entity.setPincode(userDTO.getPincode());
        entity.setCity(userDTO.getCity());
        entity.setState(userDTO.getState());
        entity.setCountry(userDTO.getCountry());

        userRepository.save(entity);
        return entity.getUserId();
    }

    public boolean loginUser(LoginDTO loginDTO) {
        Optional<UserEntity> optionalUser = userRepository.findByUserId(loginDTO.getId());
        return optionalUser.map(user -> user.getPassword().equals(loginDTO.getPassword())).orElse(false);
    }

    public UserDTO getUserProfile(String userId) {
        UserEntity entity = userRepository.findByUserId(userId)
                .orElseThrow(() -> new WecareException(ExceptionConstants.USER_NOT_FOUND.toString()));

        UserDTO dto = new UserDTO();
        dto.setUserId(entity.getUserId());
        dto.setName(entity.getName());
        dto.setPassword(entity.getPassword());
        dto.setGender(entity.getGender());
        dto.setDateOfBirth(entity.getDateOfBirth());
        dto.setMobileNumber(entity.getMobileNumber());
        dto.setEmail(entity.getEmail());
        dto.setPincode(entity.getPincode());
        dto.setCity(entity.getCity());
        dto.setState(entity.getState());
        dto.setCountry(entity.getCountry());

        return dto;
    }
//    public List<BookingDTO> getUserBookings(String userId) {
//        LocalDate today = LocalDate.now();
//        List<BookingEntity> bookings = bookRepository.findBookingByUserId(userId, today);
//        return bookings.stream().map(entity -> {
//            BookingDTO dto = new BookingDTO();
//            dto.setBookingId(entity.getBookingId());
//            dto.setUserId(entity.getUserId());
//            dto.setCoachId(entity.getCoachId());
//            dto.setAppointmentDate(entity.getAppointmentDate());
//            dto.setSlot(entity.getSlot());
//            return dto;
//        }).collect(Collectors.toList());
//    }
    public List<BookingDTO> getUserBookings(String userId) {
        // Remove the date filter from the repository call, as it's no longer needed.
        List<BookingEntity> bookings = bookRepository.findBookingByUserId(userId);
        return bookings.stream().map(entity -> {
            BookingDTO dto = new BookingDTO();
            dto.setBookingId(entity.getBookingId());
            dto.setUserId(entity.getUserId());
            dto.setCoachId(entity.getCoachId());
            dto.setAppointmentDate(entity.getAppointmentDate());
            dto.setSlot(entity.getSlot());
            return dto;
        }).collect(Collectors.toList());
    }


}
