package com.sensor.security.dao;


import com.sensor.security.entity.ConfirmationTokenEmailChange;
import com.sensor.security.entity.User;

import java.util.Optional;

public interface IConfirmationTokenEmailChangeDao {


    Optional<ConfirmationTokenEmailChange> getByTokenAndFkUser(String token, User fkUser);

    void saveConfirmationTokenEmailChange(ConfirmationTokenEmailChange cte);

    void deleteByTokenAndFkUser(String token, User fkUser);
    void deleteByFkUser(User fkUser);

    Optional<ConfirmationTokenEmailChange> getTokenByUser(User user);


}
