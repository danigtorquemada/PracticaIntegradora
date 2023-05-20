/* STATIC VALUES */

INSERT INTO gender (abbreviation, gender)
VALUES ("F", "Femenino"), ("M", "Masculino"), ("N", "No binario"), ("O","Otro");

INSERT INTO document_type (abbreviation, type_document)
VALUES ("D", "DNI"), ("N", "NIE"), ("P", "Nº pasaporte"), ("S", "Nº Seguridad social");

INSERT INTO country (abbreviation, name)
VALUES ("es", "España"), ("fr", "Francia"), ("it", "Italia"), ("pt","Portugal") ;

INSERT INTO type_road (abbreviation, type_road)
VALUES ("Av", "Avenida"), ("Cl", "Calle"), ("Gl", "Glorieta"), ("Ps", "Paseo"),
       ("Pz", "Plaza");

INSERT INTO client_type (abbreviation, client_type)
VALUES ("Br", "Bronce"), ("Pl", "Plata"), ("O", "Oro"), ("Pt", "Platino");

INSERT INTO order_state (abbreviation, type)
VALUES ("P", "En preparación"), ("T", "En tránsito"), ("X", "Extraviado"), ("E", "Entregado"), ("D", "Devuelto");

INSERT INTO credit_cart_type (abbreviation, credit_cart_type)
VALUES ("V", "VISA"), ("M", "Master Card"), ("A", "American Extress");

INSERT INTO warning_level (abbreviation, warning_level_type)
VALUES ("B", "Baja"), ("M", "Media"), ("A", "Alta");

/* TEST VALUES */

INSERT INTO user_admin (`username`) VALUES ('admin1'),('admin2'),('admin3'),('admin4');

INSERT INTO user (email, password)
VALUES ('prueba1@gmail.com', '12345'), ('prueba2@gmail.com', '12345'), ('prueba3@gmail.com', '12345'), ('prueba4@gmail.com', '12345'), ('prueba5@gmail.com', '12345');

INSERT INTO address (type_road_id, name, number, portal, floor, door, city, state, postcode)
VALUES (1,'Inventada', 15, '2', '4', 'B', 'Madrid', 'Madrid', '28700'),
       (2, 'Paraiso', 10, '3', '1', 'B', 'Madrid', 'SSRR', '28702'),
       (3, 'Padre claret', 18, '1', '2', 'C', 'Madrid', 'San blas', '28758');

INSERT INTO client (user_id, gender_id, birth_date, country_id, document_type_id, document, address_id,
                    total_spent_money, client_type_id, comments, license, first_name, last_name, phone_number,
                    entry_date, entry_user_id)
VALUES (1, 2, 20000303, 2, 1, '12342x', 1, 67, 3, 'Ningun comentario', 1, 'Dani', 'Gomez', '123456789',
        20000303, 2),
       (2, 3, 20000303, 1, 3, '1233434', 1, 99, 2, '1 Comentario chulo', 0, 'Pedro', 'Luengo', '132545657',
        20000303, 1),
        (3, 1, 20100812, 3, 2, '12342x', 2, 50, 1, 'Hola soy una prueba', 1, 'Luis', 'Fernandez', '123456789',
        20000303, 2),
        (4, 2, 20100812, 4, 1, '12342x', 1, 20, 1, 'Sigo siendo prueba', 1, 'Lucia', 'Gomez', '123456789',
        20000303, 2),
        (5, 2, 20100812, 3, 2, '12342x', 3, 160, 4, 'Ultima prueba', 1, 'Victoria', 'Federica', '123456789',
        20000303, 2);

INSERT INTO category (code, description, parent_category_id, entry_date, entry_user_id, last_modification_date,
                      last_modification_user_id)
VALUES ('Deporte', 'Deporte', null, 20001010, 1, 20010822, 2),
       ('Futbol', 'Futbol', 1, 20081010, 2, null, null),
       ('Baloncesto', 'Baloncesto', 1, 20081010, 2, 20200822, 2),
       ('Tecnologia', 'Tecnologia', null, 20081010, 2, null, null),
       ('Ordenadores', 'Ordenadores', 4, 20081010, 1, 20100810, 1),
       ('Moda', 'Moda', null, 20081010, 1, 20200822, 1);

INSERT INTO product (brand, code, comments, description, discount, evaluation, model, new_product, offer, price, min_hidden_stock, min_supplier_request, entry_date, stock)
VALUES
-- Deporte
('Nike', 'ABC123', 'Gran calidad, resistente, cómodo', 'Zapatillas deportivas', 10, 4, 'Air Max', 1, 0, 99.99, 5, 10, 20230520, 25),
('Adidas', 'DEF456', 'Ligero, transpirable, diseño atractivo', 'Camiseta de running', 20, 5, 'RunFaster', 1, 1, 29.99, 5, 10, 20230520, 25),
('Puma', 'GHI789', 'Agarre perfecto, duradero', 'Balón de fútbol', 5, 3, 'Strike', 0, 0, 19.99, 5, 10, 20230520, 25),
('Under Armour', 'JKL012', 'Resistente al agua, múltiples compartimentos', 'Mochila deportiva', 0, 4, 'SportPack', 0, 1, 39.99, 5, 10, 20230520, 25),
-- Futbol
('Nike', 'MNO345', 'Ajuste cómodo, gran control', 'Guantes de portero', 15, 4, 'GripControl', 1, 1, 49.99, 5, 10, 20230520, 25),
('Adidas', 'PQR678', 'Alta resistencia, apto para competición', 'Balón de fútbol sala', 0, 4, 'FutsalPro', 0, 0, 24.99, 5, 10, 20230520, 25),
('Puma', 'STU901', 'Ligero, excelente agarre', 'Botas de fútbol', 10, 5, 'StrikeForce', 1, 1, 79.99, 5, 10, 20230520, 25),
('New Balance', 'VWX234', 'Diseño moderno, suela amortiguadora', 'Calcetines de fútbol', 0, 3, 'DribbleSocks', 0, 0, 9.99, 5, 10, 20230520, 25),
-- Baloncesto
('Nike', 'YZA567', 'Transpirable, ajuste perfecto', 'Camiseta de baloncesto', 10, 4, 'BasketBallFit', 1, 0, 34.99, 5, 10, 20230520, 25),
('Adidas', 'BCD890', 'Buena sujeción, alta durabilidad', 'Zapatillas de baloncesto', 0, 5, 'CourtMaster', 0, 1, 89.99, 5, 10, 20230520, 25),
('Puma', 'EFG123', 'Excelente rebote, tamaño oficial', 'Balón de baloncesto', 0, 4, 'SlamDunk', 0, 1, 29.99, 5, 10, 20230520, 25),
('Under Armour', 'HIJ456', 'Protección, gran ajuste', 'Coderas para baloncesto', 5, 3, 'BasketGuard', 1, 0, 19.99, 5, 10, 20230520, 25),
-- Tecnologia
('Apple', 'KLM789', 'Alta velocidad, gran capacidad', 'Disco duro externo', 0, 5, 'iStorage', 1, 1, 199.99, 5, 10, 20230520, 25),
('Samsung', 'NOP012', 'Excelente calidad de imagen', 'Monitor LED', 10, 4, 'UltraView', 1, 0, 299.99, 5, 10, 20230520, 25),
('HP', 'QRS345', 'Rápido, gran capacidad de almacenamiento', 'Laptop', 5, 4, 'ProBook', 1, 0, 999.99, 5, 10, 20230520, 15),
('Dell', 'TUV678', 'Compacto, versátil', 'Tablet', 0, 3, 'FlexTab', 1, 1, 249.99, 5, 10, 20230520, 15),
-- Ordenadores
('Apple', 'WXY901', 'Potente, diseño elegante', 'iMac', 0, 5, 'iMacPro', 1, 1, 1999.99, 5, 10, 20230520, 15),
('Lenovo', 'ZAB234', 'Rápido, alta capacidad de almacenamiento', 'PC de escritorio', 15, 4, 'ThinkCentre', 1, 0, 799.99, 5, 10, 20230520, 15),
('HP', 'CDE567', 'Portátil, ideal para trabajo remoto', 'Laptop', 0, 4, 'Envy', 0, 1, 1299.99, 5, 10, 20230520, 15),
('Dell', 'FGH890', 'Compacto, rendimiento excepcional', 'Mini PC', 5, 3, 'OptiPlex', 0, 0, 599.99, 5, 10, 20230520, 15),
-- Moda
('Nike', 'IJK123', 'Cómodo, diseño moderno', 'Sudadera con capucha', 10, 4, 'UrbanHoodie', 1, 1, 79.99, 5, 10, 20230520, 15),
('Adidas', 'LMN456', 'Resistente al agua, estilo versátil', 'Chaqueta deportiva', 0, 4, 'AllWeather', 0, 0, 59.99, 5, 10, 20230520, 15),
('Puma', 'OPQ789', 'Ligero, transpirable', 'Camiseta de moda', 0, 3, 'UrbanTee', 0, 1, 24.99, 5, 10, 20230520, 15),
('Tommy Hilfiger', 'RST012', 'Elegante, gran calidad', 'Bolso de mano', 15, 5, 'SignatureBag', 1, 0, 149.99, 5, 10, 20230520, 15);

INSERT INTO promotion (`description`, discount, init_period, final_period, entry_date, entry_user_id,
                       last_modification_date, last_modification_user_id)
VALUES ('Promotion Futbol', 25, 20201010, 20231110, 20001010, 1, 20001010, 1),
        ('Promotion Vacia', 25, 20001010, 20001110, 20001010, 1, 20001010, 1),
       ('Promotion Moda', 50, 20001231, 20300101, 20001001, 1, 20001015, 2);

INSERT INTO product_categories (`product_id`, `categories_id`) VALUES ('1', '1'),('2', '1'),('3', '1'),('4', '1'),
                                                                      ('5', '2'),('6', '2'),('7', '2'),('8', '2'),
                                                                      ('9', '3'),('10', '3'),('11', '3'),('12', '3'),
                                                                      ('13', '4'),('14', '4'),('15', '4'),('16', '4'),
                                                                      ('17', '5'),('18', '5'),('19', '5'),('20', '5'),
                                                                      ('21', '6'),('22', '6'),('23', '6'),('24', '6');

INSERT INTO client_categories (`client_id`, `categories_id`) VALUES ('1', '1'), ('1', '2'), ('1', '3');
INSERT INTO client_categories (`client_id`, `categories_id`) VALUES ('2', '6'), ('2', '4');

INSERT INTO client_delivery_addresses (`client_id`, `addresses_id`) VALUES ('1', '1');
INSERT INTO client_delivery_addresses (`client_id`, `addresses_id`) VALUES ('1', '2');

INSERT INTO promotion_products (`promotion_id`, `product_id`) VALUES ('1', '5'), ('1', '6'), ('1', '7'),
                                                                     ('3', '22'), ('3', '23'), ('3', '24');

INSERT INTO orders (`date`, `total_price`, `client_id`, `order_state_id`, `user_admin_id`) VALUES ('20001010', '50', '1', '1', '1');
INSERT INTO orders (`date`, `total_price`, `client_id`, `order_state_id`, `user_admin_id`) VALUES ('20050105', '150', '2', '3', '2');

INSERT INTO product_order_details (`order_id`, `product_id`, `price`, `quantity`) VALUES ('1', '2', '10', '2');
INSERT INTO product_order_details (`order_id`, `product_id`, `price`, `quantity`) VALUES ('2', '1', '5', '5');
INSERT INTO product_order_details (`order_id`, `product_id`, `price`, `quantity`) VALUES ('2', '2', '8', '6');
