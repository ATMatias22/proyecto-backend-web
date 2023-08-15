
INSERT INTO role (id_role,role)
VALUES (1,'ROLE_USER'), (2,'ROLE_ADMIN');

/* la password es "password"*/
INSERT INTO users(
	 country, date_of_birth, email, enabled, lastname, name, password)
	VALUES ('Argentina','1998-05-22','matias.alejandro.torrez@gmail.com',true,'Torrez','Matias','$2a$10$zVOUymQGcepjILpBhfFareVX3zZhTLRc3PcAhIJ6krT.1nvQ5LKhK');

/* la password es "password"*/
INSERT INTO users(
	 country, date_of_birth, email, enabled, lastname, name, password)
	VALUES ('Argentina','1998-05-22','matias@gmail.com',true,'Lopez','Juan','$2a$10$zVOUymQGcepjILpBhfFareVX3zZhTLRc3PcAhIJ6krT.1nvQ5LKhK');


INSERT INTO user_roles(
fk_user,fk_role
) VALUES (1,1);

INSERT INTO user_roles(
fk_user,fk_role
) VALUES (2,1), (2,2);


INSERT INTO type_of_address(
id_type_of_address,name
) VALUES (1,'Facturacion'), (2, 'Envio');

INSERT INTO payment_method(id_payment_method, name, discount)
VALUES
(1, 'Tarjeta de credito', 0),
(2, 'Tarjeta de debito', 0),
(3, 'Efectivo', 10);

INSERT INTO shipping_method(id_shipping_method, name)
VALUES (1, 'A domicilio'), (2, 'Retiro en local');

INSERT INTO address (street, street_number, floor, apartment_number, fk_type_of_address, fk_user)
VALUES ('calle falsa', '123', null,null, 1, 1);

INSERT INTO stock (available_stock)
VALUES (20), (30);



INSERT INTO product (name,description,price,enabled,image,fk_stock,fk_user)
VALUES
('Sensor magnetico','Es un sensor que avisa cuando se cierra o abre algun objeto con estas caracteristicas',5000, true, null, 1,2),
('Sensor magnetico 2','Es un sensor que avisa cuando se cierra o abre algun objeto con estas caracteristicas',5000, true, null, 2,2);

INSERT INTO cart (fk_user, state, fk_payment_method, fk_shipping_method)
VALUES ( 1, 'ESTADO_INICIAL', null, null);

INSERT INTO cart_product ( fk_cart, fk_product, quantity,created_date)
VALUES (1,1,5, '2023-08-10 18:39:12.543702');



