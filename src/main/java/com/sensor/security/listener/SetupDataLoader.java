package com.sensor.security.listener;

import com.sensor.exception.GeneralException;
import com.sensor.security.enums.ERole;
import com.sensor.security.entity.Role;
import com.sensor.security.entity.User;
import com.sensor.security.service.IRoleService;
import com.sensor.security.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {


    boolean alreadySetup = false;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;
        Role perfilAdministrador = createRoleIfNotExists(ERole.ROLE_ADMIN);
        Role perfilUsuario = createRoleIfNotExists(ERole.ROLE_USER);

        User userAdmin = new User();
        userAdmin.setName("Matias");
        userAdmin.setLastname("Torrez");
        userAdmin.setEmail("matias.alejandro.torrez@gmail.com");
        userAdmin.setCountry("Argentina");
        userAdmin.setDateOfBirth(LocalDate.of(1998, Month.MAY,22));
        userAdmin.setPassword(passwordEncoder.encode("Password#546"));
        userAdmin.setEnabled(true);
        userAdmin.getRoles().add(perfilAdministrador);
        userAdmin.getRoles().add(perfilUsuario);

        try{
            userService.saveUser(userAdmin);
            alreadySetup = true;
        }catch (GeneralException ge){
            alreadySetup = true;
        }

    }

    @Transactional
    Role createRoleIfNotExists(ERole role) {
        try {
            return roleService.getRoleByERole(role);
        }catch (GeneralException ge){
            Role newRole = new Role();
            newRole.setERole(role);
            roleService.saveRole(newRole);
            return newRole;
        }
    }

}
