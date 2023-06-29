package com.sensor.security.dao.implementation;

import com.sensor.security.dao.IConfirmationTokenDao;
import com.sensor.security.entity.ConfirmationToken;
import com.sensor.security.entity.User;
import com.sensor.security.repository.IConfirmationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ConfirmationTokenDaoImpl implements IConfirmationTokenDao {


    @Autowired
    private IConfirmationTokenRepository confirmationTokenRepository;

    @Override
    public Optional<ConfirmationToken> getConfirmationTokenById(String id) {
        return this.confirmationTokenRepository.findByIdConfirmationToken(id);
    }

    @Override
    public void saveConfirmationToken(ConfirmationToken token) {
        this.confirmationTokenRepository.save(token);

    }

    @Override
    public boolean existsTokenForFkUser(User user) {
        return this.confirmationTokenRepository.existsByFkUser(user);
    }

    @Override
    public void deleteById(String id) {
        this.confirmationTokenRepository.deleteByIdConfirmationToken(id);
    }
}
