
DELETE FROM restaurant;

ALTER SEQUENCE global_seq RESTART WITH 1000;


INSERT INTO restaurant (name)
VALUES  ('Sergeys'),
        ('Суши-work'),
        ('Биография'),
        ('Сахар'),
        ('Wonderland');