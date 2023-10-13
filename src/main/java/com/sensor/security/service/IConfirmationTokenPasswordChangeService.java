package com.sensor.security.service;

import com.sensor.security.entity.ConfirmationTokenPasswordChange;
import com.sensor.security.entity.User;

public interface IConfirmationTokenPasswordChangeService {

    ConfirmationTokenPasswordChange getConfirmationTokenPasswordChangeByTokenAndFkUser(String token, User fkUser);

    void saveConfirmationTokenPasswordChange(ConfirmationTokenPasswordChange ct);

    void deleteByTokenAndFkUser (String token, User fkUser);

    void deleteByFkUser(User fkUser);

    ConfirmationTokenPasswordChange getConfirmationTokenPasswordChangeByUser(User user);

}
