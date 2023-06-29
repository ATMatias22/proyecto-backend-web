package com.sensor.security.service;

import com.sensor.security.entity.User;

public interface IAuthService {

    String login (User user);
    String loginAdmin (User user);
    void register(User user);
    String confirmToken(String token);
}
