package com.sensor.security.service;

import com.sensor.security.entity.User;

public interface IAuthService {

    String loginUser(User user);
    String loginAdminUser(User user);
    void registerUser(User user);
    String confirmRegisterUser(String token);
}
