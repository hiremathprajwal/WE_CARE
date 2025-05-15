package com.health.WE_CARE.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.health.WE_CARE.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByUserId(String userId);
}

