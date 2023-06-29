package com.sensor.security.service.implementation;

import com.sensor.security.dao.IConfirmationTokenDao;
import com.sensor.security.entity.ConfirmationToken;
import com.sensor.security.entity.User;
import com.sensor.security.service.IConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConfirmationTokenServiceImpl implements IConfirmationTokenService {


    @Autowired
    private IConfirmationTokenDao confirmationTokenDao;

    @Override
    public void saveConfirmationToken(ConfirmationToken token) {
        this.confirmationTokenDao.saveConfirmationToken(token);
    }

    @Override
    public Optional<ConfirmationToken> getConfirmationTokenById(String id) {
        return this.confirmationTokenDao.getConfirmationTokenById(id);
    }

    @Override
    public boolean existsTokenForFkUser(User user) {
        return this.confirmationTokenDao.existsTokenForFkUser(user);
    }

    @Override
    public void deleteById(String id) {
        this.confirmationTokenDao.deleteById(id);
    }
}
