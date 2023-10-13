package com.sensor.security.service.implementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.sensor.exception.GeneralException;
import com.sensor.security.MainUser;
import com.sensor.security.entity.ConfirmationTokenEmailChange;
import com.sensor.security.entity.ConfirmationTokenPasswordChange;
import com.sensor.security.service.IConfirmationTokenEmailChangeService;
import com.sensor.security.service.IConfirmationTokenPasswordChangeService;
import com.sensor.security.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sensor.security.dao.IUserDao;
import com.sensor.security.entity.User;
import com.sensor.security.service.IUserService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;

	private PasswordEncoder passwordEncoder;

	@Autowired
	private IEmailService emailService;

	@Autowired
	private IConfirmationTokenEmailChangeService confirmationTokenEmailChangeService;

	@Autowired
	private IConfirmationTokenPasswordChangeService confirmationTokenPasswordChangeService;

	public UserServiceImpl(@Lazy PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public List<User> getAllUsers() {
		return this.userDao.getAllUsers();
	}

	@Override
	public User getUserById(Long userId) {
		return  this.userDao.getUserById(userId).orElseThrow(() -> new GeneralException(HttpStatus.BAD_REQUEST, "No se encontro al usuario con id: "+userId));

	}

	@Override
	public void saveUser(User user) {
		userDao.getUserByEmail(user.getEmail()).ifPresent(u -> {
			if(!u.getEnabled()){
				throw new GeneralException(HttpStatus.BAD_REQUEST,  u.getEmail() + " ya fue registrado verifique su casilla de mails para confirmar");
			}
			throw new GeneralException(HttpStatus.BAD_REQUEST, "El email " + u.getEmail() + " ya existe");
		});
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userDao.saveUser(user);
	}

	@Override
	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email).orElseThrow(() -> new GeneralException(HttpStatus.BAD_REQUEST, "No se encontro el usuario con email: " + email));
	}

	@Override
	public User getUserLoggedInByEmailInToken() {
		MainUser mu = (MainUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return  this.userDao.getUserById(mu.getId()).orElseThrow(() -> new GeneralException(HttpStatus.BAD_REQUEST, "No se encontro al usuario logueado por favor inicie sesion de vuelta"));
	}

	@Override
	public Integer enableUser(String email) {
		return userDao.enableUser(email);
	}

	@Transactional
	@Override
	public void modifyData(User modifiedUser) {

		MainUser mu = (MainUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String currentEmail = mu.getUsername();
		String newEmail = modifiedUser.getEmail();

		User user = this.getUserByEmail(currentEmail);

		if (!currentEmail.equals(newEmail)) {

			this.userDao.getUserByEmail(newEmail).ifPresent(u -> {
				throw new GeneralException(HttpStatus.BAD_REQUEST, "El email " + u.getEmail() + " ya existe en el sistema");
			});

			ConfirmationTokenEmailChange cte = null;
			String token = UUID.randomUUID().toString();

			try {
				cte = this.confirmationTokenEmailChangeService.getConfirmationTokenEmailChangeByUser(user);
				cte.setToken(token);
				cte.setCreatedAt(LocalDateTime.now());
				cte.setNewEmail(newEmail);
				this.confirmationTokenEmailChangeService.saveConfirmationTokenEmailChange(cte);
			} catch (GeneralException ge) {
				ConfirmationTokenEmailChange confirmationToken = new ConfirmationTokenEmailChange(
						token,
						LocalDateTime.now(),
						user,
						newEmail
				);
				this.confirmationTokenEmailChangeService.saveConfirmationTokenEmailChange(confirmationToken);
			}
			emailService.send("Cambio de email",newEmail, buildEmailForChangeEmail(token, currentEmail));
		}


		user.setName(modifiedUser.getName());
		user.setLastname(modifiedUser.getLastname());
		user.setCountry(modifiedUser.getCountry());
		user.setDateOfBirth(modifiedUser.getDateOfBirth());
		user.setUpdated(LocalDateTime.now());

		this.userDao.saveUser(user);
	}


	@Transactional
	@Override
	public void confirmTokenEmailChange(String token) {

		MainUser mu = (MainUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = this.getUserByEmail(mu.getUsername());


		ConfirmationTokenEmailChange confirmationToken = this.confirmationTokenEmailChangeService
				.getConfirmationTokenEmailChangeByTokenAndFkUser(token, user);

		User userLinkedInToken = confirmationToken.getFkUser();
		userLinkedInToken.setEmail(confirmationToken.getNewEmail());
		userLinkedInToken.setUpdated(LocalDateTime.now());
		this.userDao.saveUser(userLinkedInToken);

		this.confirmationTokenPasswordChangeService.deleteByFkUser(user);
		this.confirmationTokenEmailChangeService.deleteByTokenAndFkUser(token, user);

	}




	@Transactional
	@Override
	public void modifyPassword(String oldPassword, String newPassword) {

		MainUser mu = (MainUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = this.getUserByEmail(mu.getUsername());

		if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
			throw new GeneralException(HttpStatus.BAD_REQUEST, "Password incorrecta");
		} else if (passwordEncoder.matches(newPassword, user.getPassword())) {
			throw new GeneralException(HttpStatus.BAD_REQUEST, "La nueva password no puede ser la misma que la anterior");
		}

		ConfirmationTokenPasswordChange ct = null;
		String token = UUID.randomUUID().toString();
		String newPasswordEncoded = this.passwordEncoder.encode(newPassword);

		try {
			ct = this.confirmationTokenPasswordChangeService.getConfirmationTokenPasswordChangeByUser(user);
			ct.setToken(token);
			ct.setCreatedAt(LocalDateTime.now());
			ct.setNewPassword(newPasswordEncoded);
		} catch (GeneralException ge) {
			ct = new ConfirmationTokenPasswordChange(
					token,
					LocalDateTime.now(),
					user,
					newPasswordEncoded
			);
		}
		this.confirmationTokenPasswordChangeService.saveConfirmationTokenPasswordChange(ct);
		emailService.send("Cambio de contraseña", mu.getUsername(), buildEmailForChangePassword(token, user.getName()));

	}



	@Transactional
	@Override
	public void confirmTokenPasswordChange(String token) {

		MainUser mu = (MainUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = this.getUserByEmail(mu.getUsername());

		ConfirmationTokenPasswordChange confirmationToken = this.confirmationTokenPasswordChangeService
				.getConfirmationTokenPasswordChangeByTokenAndFkUser(token,user);

		User userLinkedInToken = confirmationToken.getFkUser();

		userLinkedInToken.setPassword(confirmationToken.getNewPassword());
		userLinkedInToken.setUpdated(LocalDateTime.now());
		this.userDao.saveUser(userLinkedInToken);

		this.confirmationTokenPasswordChangeService.deleteByTokenAndFkUser(token, user);
	}











	private String buildEmailForChangePassword(String token, String name) {
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
				"                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirma tu cambio de contraseña</span>\n" +
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
				"            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hola " + name+", </p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Alguien en tu cuenta ha tratado de cambiar la contraseña, en caso de que haya realizado este cambio, por favor hace copia en la aplicacion el siguiente token para confirmar el cambio de contraseña: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">" + token + "</p></blockquote>\n </p>" +
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




	private String buildEmailForChangeEmail(String token, String oldEmail) {
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
				"                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirma tu cambio de email</span>\n" +
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
				"            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hola,</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Tu cuenta en nuestra aplicacion que esta registrada con el email " + oldEmail + " trato de cambiar el email, y colocar esta cuenta como nuevo email vinculado a esa cuenta, en caso de que seas tu, por favor copia el siguiente codigo en la aplicacion para confirmar el cambio de email: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">"+token+"</p></blockquote>\n </p>" +
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
