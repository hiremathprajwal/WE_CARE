package com.health.WE_CARE.service;

import com.health.WE_CARE.dto.BookingDTO;
import com.health.WE_CARE.entity.BookingEntity;
import com.health.WE_CARE.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Book an appointment
    public String bookAppointment(String userId, String coachId, LocalDate appointmentDate, String slot) {
        BookingEntity entity = new BookingEntity();
        entity.setUserId(userId);
        entity.setCoachId(coachId);
        entity.setAppointmentDate(appointmentDate);
        entity.setSlot(slot);
        bookRepository.save(entity);
        return "Appointment booked successfully.";
    }

    // Reschedule an appointment
    public String rescheduleAppointment(Integer bookingId, LocalDate appointmentDate, String slot) {
        Optional<BookingEntity> optionalBooking = bookRepository.findById(bookingId);
        if (optionalBooking.isPresent()) {
            BookingEntity entity = optionalBooking.get();
            entity.setAppointmentDate(appointmentDate);
            entity.setSlot(slot);
            bookRepository.save(entity);
            return "Appointment rescheduled successfully.";
        } else {
            return "Booking not found.";
        }
    }

    // Cancel an appointment
    public String cancelAppointment(Integer bookingId) {
        if (bookRepository.existsById(bookingId)) {
            bookRepository.deleteById(bookingId);
            return "Appointment cancelled successfully.";
        } else {
            return "Booking not found.";
        }
    }

    // Find booking by ID
    public BookingDTO findByBookingId(Integer bookingId) {
        return bookRepository.findById(bookingId).map(entity -> {
            BookingDTO dto = new BookingDTO();
            dto.setBookingId(entity.getBookingId());
            dto.setUserId(entity.getUserId());
            dto.setCoachId(entity.getCoachId());
            dto.setAppointmentDate(entity.getAppointmentDate());
            dto.setSlot(entity.getSlot());
            return dto;
        }).orElse(null);
    }

    // Find all future bookings for a user
//    public List<BookingDTO> findBookingByUserId(String userId) {
//        return bookRepository.findBookingByUserId(userId, LocalDate.now()).stream().map(entity -> {
//            BookingDTO dto = new BookingDTO();
//            dto.setBookingId(entity.getBookingId());
//            dto.setUserId(entity.getUserId());
//            dto.setCoachId(entity.getCoachId());
//            dto.setAppointmentDate(entity.getAppointmentDate());
//            dto.setSlot(entity.getSlot());
//            return dto;
//        }).collect(Collectors.toList());
//    }
    public List<BookingDTO> findBookingByUserId(String userId) {
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

    // Find all future bookings for a coach
    public List<BookingDTO> findBookingByCoachId(String coachId) {
        return bookRepository.findBookingByCoachId(coachId, LocalDate.now()).stream().map(entity -> {
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
