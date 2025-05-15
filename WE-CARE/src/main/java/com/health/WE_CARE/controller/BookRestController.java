package com.health.WE_CARE.controller;

import com.health.WE_CARE.dto.BookingDTO;
import com.health.WE_CARE.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookRestController {

    @Autowired
    private BookService bookService;

    // Book an appointment
    @PostMapping("/users/{userId}/coaches/{coachId}")
    public String bookAppointment(@PathVariable String userId,
                                  @PathVariable String coachId,
                                  @RequestParam("date") String date,
                                  @RequestParam("slot") String slot) {
        LocalDate appointmentDate = LocalDate.parse(date);
        return bookService.bookAppointment(userId, coachId, appointmentDate, slot);
    }

    // Reschedule an appointment
    @PutMapping("/{bookingId}")
    public String rescheduleAppointment(@PathVariable Integer bookingId,
                                        @RequestParam("date") String date,
                                        @RequestParam("slot") String slot) {
        LocalDate appointmentDate = LocalDate.parse(date);
        return bookService.rescheduleAppointment(bookingId, appointmentDate, slot);
    }

    // Cancel an appointment
    @DeleteMapping("/{bookingId}")
    public String cancelAppointment(@PathVariable Integer bookingId) {
        return bookService.cancelAppointment(bookingId);
    }

    // Get booking details by bookingId (optional)
    @GetMapping("/{bookingId}")
    public BookingDTO getBookingById(@PathVariable Integer bookingId) {
        return bookService.findByBookingId(bookingId);
    }

    // Get upcoming bookings for a user
    @GetMapping("/users/{userId}")
    public List<BookingDTO> getBookingsByUser(@PathVariable String userId) {
        return bookService.findBookingByUserId(userId);
    }

    // Get upcoming bookings for a coach
    @GetMapping("/coaches/{coachId}")
    public List<BookingDTO> getBookingsByCoach(@PathVariable String coachId) {
        return bookService.findBookingByCoachId(coachId);
    }
}
