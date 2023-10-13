package com.sensor.security.dao;

import com.sensor.security.entity.ConfirmationTokenPasswordChange;
import com.sensor.security.entity.User;

import java.util.Optional;

public interface IConfirmationTokenPasswordChangeDao {

    Optional<ConfirmationTokenPasswordChange> getByTokenAndFkUser(String token, User fkUser);

    void saveConfirmationTokenPasswordChange(ConfirmationTokenPasswordChange ct);

    void deleteByTokenAndFkUser(String token, User fkUser);
    void deleteByFkUser(User fkUser);

    Optional<ConfirmationTokenPasswordChange> getTokenByUser(User user);





}
