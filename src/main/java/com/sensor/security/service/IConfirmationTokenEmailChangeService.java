package com.sensor.security.service;


import com.sensor.security.entity.ConfirmationTokenEmailChange;
import com.sensor.security.entity.User;

public interface IConfirmationTokenEmailChangeService {

    ConfirmationTokenEmailChange getConfirmationTokenEmailChangeByTokenAndFkUser(String token, User fkUser);

    void saveConfirmationTokenEmailChange(ConfirmationTokenEmailChange cte);

    void deleteByTokenAndFkUser (String token, User fkUser);
    void deleteByFkUser (User fkUser);

    ConfirmationTokenEmailChange getConfirmationTokenEmailChangeByUser(User user);

}
