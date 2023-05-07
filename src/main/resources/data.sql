/* STATIC VALUES */

INSERT INTO gender (abbreviation, gender)
VALUES ("F", "Femenino"), ("M", "Masculino"), ("N", "No binario"), ("O","Otro");

INSERT INTO document_type (abbreviation, type_document)
VALUES ("D", "DNI"), ("N", "NIE"), ("P", "Nº pasaporte"), ("S", "Nº Seguridad social");

INSERT INTO country (abbreviation, name)
VALUES ("es", "España"), ("fr", "Francia"), ("Italia", "it"), ("pt","Portugal") ;

INSERT INTO type_road (abbreviation, type_road)
VALUES ("Av", "Avenida"), ("Cl", "Calle"), ("Gl", "Glorieta"), ("Ps", "Paseo"),
       ("Pz", "Plaza");

INSERT INTO client_type (abbreviation, client_type)
VALUES ("Br", "Bronce"), ("Pl", "Plata"), ("O", "Oro"), ("Pt", "Platino");

/* TEST VALUES */


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

INSERT INTO category (code, `description`, parent_category_id, entry_date, entry_user_id, last_modification_date,
                      last_modification_user_id)
VALUES ('Deporte', 'Deporte', null, 20001010, 1, 20010822, 2),
       ('Futbol', 'Futbol', 1, 20081010, 2, 20200822, 2);