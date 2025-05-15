package com.health.WE_CARE.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.health.WE_CARE.entity.BookingEntity;

public interface BookRepository extends JpaRepository<BookingEntity, Integer> {

    Optional<BookingEntity> findByUserId(String userId);

//    @Query("SELECT b FROM BookingEntity b WHERE b.userId = :userId AND b.appointmentDate >= :today")
//    List<BookingEntity> findBookingByUserId(@Param("userId") String userId, @Param("today") LocalDate today);
    @Query("SELECT b FROM BookingEntity b WHERE b.userId = :userId")
        List<BookingEntity> findBookingByUserId(@Param("userId") String userId);


    @Query("SELECT b FROM BookingEntity b WHERE b.coachId = :coachId AND b.appointmentDate >= :today")
    List<BookingEntity> findBookingByCoachId(@Param("coachId") String coachId, @Param("today") LocalDate today);

    @Query("SELECT b FROM BookingEntity b WHERE b.userId = :userId AND b.appointmentDate = :appointmentDate AND b.slot = :slot")
    BookingEntity findAllBookings(@Param("userId") String userId,
                                  @Param("appointmentDate") LocalDate appointmentDate,
                                  @Param("slot") String slot);


}

