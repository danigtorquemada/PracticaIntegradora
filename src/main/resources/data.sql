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

/* TEST VALUES */

INSERT INTO user_admin (`username`) VALUES ('admin1'),('admin2'),('admin3'),('admin4');

INSERT INTO user (email, password)
VALUES ('prueba1@gmail.com', '12345'), ('prueba2@gmail.com', '12345');

INSERT INTO address (type_road_id, number, portal, floor, door, city, state, postcode)
VALUES (1, 15, '2', '4', 'B', 'Madrid', 'Madrid', '28700'),
       (2, 10, '3', '1', 'B', 'Madrid', 'SSRR', '28702');

INSERT INTO client (user_id, gender_id, birth_date, country_id, document_type_id, document, address_id,
                    total_spent_money, client_type_id, comments, license, first_name, last_name, phone_number,
                    entry_date, entry_user_id, last_modification_date, last_modification_user_id, removed_date,
                    removed_user_id)
VALUES (1, 2, 20000303, 2, 1, '12342x', 1, 67, 3, 'Ningun comentario', 1, 'Dani', 'Gomez', '123456789',
        20000303, 2, 20000303, 2, 20000303, 2),
       (2, 3, 20000303, 1, 3, '1233434', 1, 99, 3, '1 Comentario chulo', 0, 'Pedro', 'Luengo', '132545657',
        20000303, 1, 20000303, 1, 20000303, 1);

INSERT INTO category (code, description, parent_category_id, entry_date, entry_user_id, last_modification_date,
                      last_modification_user_id)
VALUES ('Deporte', 'Deporte', null, 20001010, 1, 20010822, 2),
       ('Futbol', 'Futbol', 1, 20081010, 2, 20200822, 2);

INSERT INTO promotion (`description`, discount, init_period, final_period, entry_date, entry_user_id,
                       last_modification_date, last_modification_user_id)
VALUES ('Promotion 1', 25, 20001010, 20001110, 20001010, 1, 20001010, 1),
       ('Promotion 2', 50, 20001231, 20030101, 20001001, 1, 20001015, 2);

INSERT INTO product (`entry_date`, `last_modification_date`, `brand`, `code`, `comments`, `description`, `evaluation`, `min_hidden_stock`, `min_supplier_request`, `model`, `new_product`, `offer`, `price`, `stock`, `entry_user_id`, `last_modification_user_id`, `hidden`)
VALUES ('20001010', '20001010', 'Adidas', 'A10', 'Sin comentarios', 'Zapatillas Adidas', '4', '2', '2', 'AirMax', false, false, '10', '5', '1', '1', false);
INSERT INTO product (`entry_date`, `last_modification_date`, `brand`, `code`, `comments`, `description`, `discount`, `evaluation`, `new_product`, `offer`, `price`, `stock`, `entry_user_id`, `last_modification_user_id`, `hidden`)
VALUES ('20080220', '20080221', 'Polo', 'P8', 'Bonito', 'Polo Gris', '20', '2', true, false, '20', '10', '2', '2', false);

INSERT INTO product_categories (`product_id`, `categories_id`) VALUES ('1', '1');
INSERT INTO product_categories (`product_id`, `categories_id`) VALUES ('1', '2');
INSERT INTO product_categories (`product_id`, `categories_id`) VALUES ('2', '1');

INSERT INTO client_categories (`client_id`, `categories_id`) VALUES ('1', '2');
INSERT INTO client_categories (`client_id`, `categories_id`) VALUES ('2', '1');
INSERT INTO client_categories (`client_id`, `categories_id`) VALUES ('2', '2');

INSERT INTO client_delivery_addresses (`client_id`, `addresses_id`) VALUES ('1', '1');
INSERT INTO client_delivery_addresses (`client_id`, `addresses_id`) VALUES ('1', '2');

INSERT INTO promotion_products (`promotion_id`, `product_id`) VALUES ('1', '1');
INSERT INTO promotion_products (`promotion_id`, `product_id`) VALUES ('1', '2');

INSERT INTO order_state (abbreviation, type)
VALUES ("P", "En preparación"), ("T", "En tránsito"), ("X", "Extraviado"), ("E", "Entregado"), ("D", "Devuelto");

INSERT INTO orders (`date`, `total_price`, `client_id`, `order_state_id`, `user_admin_id`) VALUES ('20001010', '50', '1', '1', '1');
INSERT INTO orders (`date`, `total_price`, `client_id`, `order_state_id`, `user_admin_id`) VALUES ('20050105', '150', '2', '3', '2');

INSERT INTO product_order_details (`order_id`, `product_id`, `price`, `quantity`) VALUES ('1', '2', '10', '2');
INSERT INTO product_order_details (`order_id`, `product_id`, `price`, `quantity`) VALUES ('2', '1', '5', '5');
INSERT INTO product_order_details (`order_id`, `product_id`, `price`, `quantity`) VALUES ('2', '2', '8', '6');

INSERT INTO credit_cart_type (abbreviation, credit_cart_type)
VALUES ("V", "VISA"), ("M", "Master Card"), ("A", "American Extress");

INSERT INTO warning_level (abbreviation, warning_level_type)
VALUES ("B", "Baja"), ("M", "Media"), ("A", "Alta");

