package com.sensor.security.dao;

import com.sensor.security.entity.ConfirmationToken;
import com.sensor.security.entity.User;

import java.util.Optional;

public interface IConfirmationTokenDao {

    Optional<ConfirmationToken> getConfirmationTokenById(String id);

    void saveConfirmationToken(ConfirmationToken token);

    boolean existsTokenForFkUser(User user);

    void deleteById(String id);
}
