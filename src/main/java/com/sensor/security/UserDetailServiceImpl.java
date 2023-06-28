package com.sensor.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import com.sensor.exception.GeneralException;
import com.sensor.exception.constants.ExceptionMessage;
import com.sensor.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sensor.dao.IUserDao;
import com.sensor.entity.Role;
import com.sensor.security.entity.User;


@Service
public class UserDetailServiceImpl implements UserDetailsService {

	
	@Autowired
	private IUserService userService;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = null;
		try {
			user = userService.getUserByEmail(email);
		}catch (GeneralException ge){
			throw new GeneralException(HttpStatus.UNAUTHORIZED, ExceptionMessage.BAD_CREDENTIALS);
		}

		if (!user.getEnabled() && this.confirmationTokenService.existsTokenForFkUser(user)) {
			throw new UnabledAccountException(HttpStatus.UNAUTHORIZED, ExceptionMessage.UNABLED_ACCOUNT);
		}

		return MainUser.build(usuario);
	}
	

}
