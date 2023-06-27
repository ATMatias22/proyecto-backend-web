package com.sensor.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sensor.dao.IUserDao;
import com.sensor.persistence.entity.Role;
import com.sensor.persistence.entity.User;


@Service
public class CustomUserDetailsService implements UserDetailsService {

	
	@Autowired
	private IUserDao IUserDao;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User usuario = IUserDao.getUserByEmail(email).get();
		return new org.springframework.security.core.userdetails.User(usuario.getEmail(),usuario.getPassword(),mappingTypeUser(usuario.getRole()));
	}
	
	private Collection<? extends GrantedAuthority> mappingTypeUser(Role rol){
		ArrayList<Role> roles = new ArrayList<>();
		roles.add(rol);
		return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
	}

}
