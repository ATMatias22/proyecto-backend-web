
INSERT INTO role (id_role,role)
VALUES (1,'ROLE_USER'), (2,'ROLE_ADMIN');

/* la password es "password"*/
INSERT INTO users(
	id_user, country, date_of_birth, email, enabled, lastname, name, password)
	VALUES (1,'Argentina','1998-05-22','matias.alejandro.torrez@gmail.com',true,'Torrez','Matias','$2a$10$zVOUymQGcepjILpBhfFareVX3zZhTLRc3PcAhIJ6krT.1nvQ5LKhK');


INSERT INTO user_roles(
fk_user,fk_role
) VALUES (1,1);


INSERT INTO type_of_address(
id_type_of_address,name
) VALUES (1,'Casa'), (2, 'PH'), (3, 'Departamento'), (4,'Otro');