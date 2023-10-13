package com.sensor.security.service.implementation;

import com.sensor.entity.Cart;
import com.sensor.exception.GeneralException;
import com.sensor.exception.constants.ExceptionMessage;
import com.sensor.security.entity.ConfirmationToken;
import com.sensor.security.enums.ERole;
import com.sensor.security.entity.Role;
import com.sensor.security.entity.User;
import com.sensor.security.jwt.JwtProvider;
import com.sensor.security.service.*;
import com.sensor.service.ICartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Service
public class AuthServiceImpl implements IAuthService {


    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IEmailService emailService;

    @Autowired
    private ICartService cartService;
    @Autowired
    private IConfirmationTokenService confirmationTokenService;

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Override
    public String loginUser(User user) {

        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

            boolean existRole = authorities.stream().anyMatch(authority -> authority.getAuthority().equalsIgnoreCase(ERole.ROLE_USER.toString()));
            if(existRole){
                SecurityContextHolder.getContext().setAuthentication(authentication);
                return jwtProvider.generateToken(authentication);
            } else {
                throw new BadCredentialsException("Algunas de sus credenciales son incorrectas");
            }
        } catch (InternalAuthenticationServiceException ie) {
            throw new GeneralException(HttpStatus.UNAUTHORIZED, ie.getMessage());
        } catch (AuthenticationException ae) {
            logger.warn(ae.getMessage());
            throw new GeneralException(HttpStatus.UNAUTHORIZED, ExceptionMessage.BAD_CREDENTIALS);
        }

    }

    @Override
    public String loginAdminUser(User user) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

            boolean existRole = authorities.stream().anyMatch(authority -> authority.getAuthority().equalsIgnoreCase(ERole.ROLE_ADMIN.toString()));
            if(existRole){
                SecurityContextHolder.getContext().setAuthentication(authentication);
                return jwtProvider.generateToken(authentication);
            } else {
                throw new BadCredentialsException("Algunas de sus credenciales son incorrectas");
            }
        } catch (InternalAuthenticationServiceException ie) {
            throw new GeneralException(HttpStatus.UNAUTHORIZED, ie.getMessage());
        } catch (AuthenticationException ae) {
            logger.warn(ae.getMessage());
            throw new GeneralException(HttpStatus.UNAUTHORIZED, ExceptionMessage.BAD_CREDENTIALS);
        }
    }


    @Override
    @Transactional
    public void registerUser(User user) {
        User userForLogin = null;

        try {
            userForLogin = (User) user.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Problemas con el servidor");
        }

        Role role = roleService.getRoleByERole(ERole.ROLE_USER);
        user.getRoles().add(role);
        this.userService.saveUser(user);

        String token = this.loginUser(userForLogin);

        String id = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                id,
                token,
                LocalDateTime.now(),
                user
        );
        confirmationTokenService.saveConfirmationToken(
                confirmationToken);
        emailService.sendEmail("Registro",user.getEmail(), buildEmail(user.getName(), id));
    }

    @Override
    @Transactional
    public String confirmRegisterUser(String id) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getConfirmationTokenById(id)
                .orElseThrow(() ->
                        new IllegalStateException("token no encontrado"));

        User user = confirmationToken.getFkUser();
        userService.enableUser(user.getEmail());


        //Creamos el nuevo carrito para que el usuario pueda realizar compras.
        Cart cart = new Cart();
        cart.setUser(user);

        this.cartService.saveCart(cart);

        this.confirmationTokenService.deleteConfirmationTokenById(id);

        return confirmationToken.getToken();
    }


    private String buildEmail(String name, String idToken) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirma tu registro</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hola " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Gracias por estar registrandote en nuestra aplicacion. Para terminar este proceso te pedimos que coloques el siguiente codigo en la aplicacion: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">"+ idToken +"</p></blockquote>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }

}
