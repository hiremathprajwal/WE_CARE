package com.health.WE_CARE.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.health.WE_CARE.entity.CoachEntity;

public interface CoachRepository extends JpaRepository<CoachEntity, String> {
    Optional<CoachEntity> findByCoachId(String coachId);
}
