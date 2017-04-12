DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (user_id, datetime, description, calories) VALUES
  (100000, TIMESTAMP '2017-03-10 10:00:00', 'Завтрак', 400),
  (100000, TIMESTAMP '2017-03-10 14:00:00', 'Обед', 800),
  (100000, TIMESTAMP '2017-03-10 19:00:00', 'Ужин', 1200),
  (100000, TIMESTAMP '2017-03-11 10:00:00', 'Завтрак', 400),
  (100000, TIMESTAMP '2017-03-11 14:00:00', 'Обед', 800),
  (100000, TIMESTAMP '2017-03-11 19:00:00', 'Ужин', 600);

INSERT INTO meals (user_id, datetime, description, calories) VALUES
  (100001, TIMESTAMP '2017-03-10 10:00:00', 'Завтрак', 1600),
  (100001, TIMESTAMP '2017-03-11 14:00:00', 'Обед', 2001);