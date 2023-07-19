
INSERT INTO role (id_role,role)
VALUES (1,'ROLE_USER');
INSERT INTO role (id_role,role)
VALUES (2,'ROLE_ADMIN');


/* la password es "password"*/
INSERT INTO users(
	id_user, country, date_of_birth, email, enabled, lastname, name, password)
	VALUES (1,'Argentina','1998-05-22','matias.alejandro.torrez@gmail.com',true,'Torrez','Matias','$2a$10$zVOUymQGcepjILpBhfFareVX3zZhTLRc3PcAhIJ6krT.1nvQ5LKhK');


INSERT INTO user_roles(
fk_user,fk_role
) VALUES (1,1);