package com.sensor.security.repository;

import com.sensor.security.entity.ConfirmationToken;
import com.sensor.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface IConfirmationTokenRepository extends JpaRepository<ConfirmationToken, String> {

    Optional<ConfirmationToken> findByIdConfirmationToken(String id);

    boolean existsByFkUser(User user);

    @Modifying
    void deleteByIdConfirmationToken(String id);

}
