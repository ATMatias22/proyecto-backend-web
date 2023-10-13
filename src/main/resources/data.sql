
INSERT INTO role (id_role,role)
VALUES (1,'ROLE_USER'), (2,'ROLE_ADMIN');

/* la password es "Password123%"*/
INSERT INTO users(
	 country, date_of_birth, email, enabled, lastname, name, password)
	VALUES ('Argentina','1998-05-22','matias.alejandro.torrez@gmail.com',true,'Torrez','Matias','$2a$10$gTrVzE2JEKDnDyuD7vUOEeLF.4fdVJxP2EwwRax5Tz.guA6HpZ2Bq');

/* la password es "Password123%"*/
INSERT INTO users(
	 country, date_of_birth, email, enabled, lastname, name, password)
	VALUES ('Argentina','1998-05-22','matias@gmail.com',true,'Lopez','Juan','$2a$10$gTrVzE2JEKDnDyuD7vUOEeLF.4fdVJxP2EwwRax5Tz.guA6HpZ2Bq');


INSERT INTO user_roles(
fk_user,fk_role
) VALUES (1,1);

INSERT INTO user_roles(
fk_user,fk_role
) VALUES (2,1), (2,2);


INSERT INTO type_of_address(name
) VALUES ('Facturacion'), ( 'Envio');

INSERT INTO payment_method(name, discount)
VALUES
('Tarjeta', 10),
('Mercado Pago', 0),
('Efectivo', 20);

INSERT INTO shipping_method(name)
VALUES ('A domicilio'), ('Retiro en local');

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



