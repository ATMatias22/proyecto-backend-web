package com.sensor.security.repository;

import com.sensor.security.entity.ConfirmationTokenPasswordChange;
import com.sensor.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IConfirmationTokenPasswordChangeRepository extends JpaRepository<ConfirmationTokenPasswordChange, Long> {

    Optional<ConfirmationTokenPasswordChange> findByTokenAndFkUser(String token, User fkUser);

    void deleteByTokenAndFkUser(String token, User fkUser);
    void deleteByFkUser(User fkUser);

    Optional<ConfirmationTokenPasswordChange>  findByFkUser(User user);

}
