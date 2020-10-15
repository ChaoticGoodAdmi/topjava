DELETE
FROM user_roles;
DELETE
FROM users;
DELETE
FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (user_id, date_time, description, calories)
VALUES (100000, '2020-10-15 10:00', 'Breakfast', 500),
       (100000, '2020-10-15 13:00', 'Lunch', 1000),
       (100000, '2020-10-15 20:00', 'Dinner', 500),
       (100000, '2020-10-16 00:00', 'Midnight snack', 100),
       (100000, '2020-10-16 10:00', 'Breakfast', 1000),
       (100000, '2020-10-16 13:00', 'Lunch', 500),
       (100000, '2020-10-16 20:00', 'Dinner', 410),
       (100001, '2020-10-16 10:00', 'Admin Breakfast', 1000),
       (100001, '2020-10-16 18:00', 'Admin Dinner', 1000);