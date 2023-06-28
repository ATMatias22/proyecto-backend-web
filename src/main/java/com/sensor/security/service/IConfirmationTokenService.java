package com.sensor.security.service;

import com.sensor.security.entity.ConfirmationToken;
import com.sensor.security.entity.User;

import java.util.Optional;

public interface IConfirmationTokenService {

    void saveConfirmationToken(ConfirmationToken token);

    Optional<ConfirmationToken> getConfirmationTokenById(String id);

    boolean existsTokenForFkUser (User user);

    void deleteById(String id);
}
