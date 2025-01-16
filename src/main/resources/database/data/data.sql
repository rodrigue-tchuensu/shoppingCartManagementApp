
USE shopping_cart_management_db;

INSERT INTO customer (first_name, last_name, email, address)
VALUES ('Juanita', 'Pavia', 'jpavia0@mozilla.com', '77020 Brown Road'),
         ('Sigfrid', 'de Quincey', 'sdequincey1@liveinternet.ru', '12 Lillian Parkway'),
         ('Merline', 'Bordis', 'mbordis2@lycos.com', '6921 Grim Center'),
         ('Darsey', 'Greg', 'dgreg3@theglobeandmail.com', '94303 Namekagon Terrace');

INSERT INTO product (name, product_number, price, product_type, rating)
VALUES ('Iphone 15', 'TCH100', '1249.99', 'Tech', '4.5'),
       ('Iphone 14', 'TCH102', '1000.99', 'Tech', '4.0'),
       ('Samsung Galaxy 23', 'TCH103', '999.99', 'Tech', '4.5'),
       ('computer', 'TCH104', '1249.99', 'Tech', '4.0'),
       ('Volkswagen', 'VEH120', '2800.99', 'Fahrzeug', '4.5'),
       ('Mercedes-Benz', 'VEH121', '3499.99', 'Fahrzeug', '4.8'),
       ('BMW', 'VEH122', '2799.99', 'Fahrzeug', '4.0'),
       ('Audi', 'VEH123', '2499.99', 'Fahrzeug', '4.5'),
       ('Opel', 'VEH124', '1999.99', 'Fahrzeug', '4.4'),
       ('Shampoo', 'BEU230', '19.90', 'Beauty', '4.8'),
       ('Shampoo', 'BEU231', '9.99', 'Beauty', '4.5'),
       ('Shampoo', 'BEU232', '4.99', 'Beauty', '4.0'),
       ('Perfume', 'BEU233', '14.99', 'Beauty', '4.0'),
       ('Perfume', 'BEU234', '49.99', 'Beauty', '4.0'),
       ('Eyeliner', 'BEU235', '30.00', 'Beauty', '4.5'),
       ('Eyeliner', 'BEU236', '12.50', 'Beauty', '4.4'),
       ('Penthouse', 'IMMO541', '2000.00', 'Immobilie', '4.5');


