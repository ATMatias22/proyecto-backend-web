package com.sensor.security;

import com.sensor.security.entity.Role;
import com.sensor.security.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
public class MainUser implements UserDetails {

    private Long Id;
    private String email;
    private String password;
    private Collection<GrantedAuthority> authorities;

    public static MainUser build(User user){
        return new MainUser(user.getUserId(),user.getEmail(), user.getPassword(), MainUser.mappingToGrantedAuthorities(user.getRoles()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return Id;
    }

    private static Collection<GrantedAuthority> mappingToGrantedAuthorities(Collection<Role> roles){
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getERole().toString())).collect(Collectors.toList());
    }

}
