INSERT INTO users(username, password, role)
VALUES ('admin', 'admin', 'ADMIN'),
       ('user', 'user', 'USER'),
       ('Andrey', 'Andrey', 'USER'),
       ('Igor', 'Igor', 'USER'),
       ('Artem', 'Artem', 'USER'),
       ('Valery Petrov', 'Petrov', 'USER'),
       ('Maksim Ivanov', 'Ivanov', 'USER'),
       ('Elena Matsiyuk', 'Matsiyuk', 'USER'),
       ('Ivanov Ivan', 'Artem', 'USER'),
       ('Kio Joino', 'Artem', 'USER'),
       ('Evangelia Vlodny', 'Artem', 'USER'),
       ('Viktory Ghuchi', 'Artem', 'USER'),
       ('Artem Valenok', 'Artem', 'USER'),
       ('Artem Petrob', 'Artem', 'USER'),
       ('Olga Kiok', 'Artem', 'USER'),
       ('Mixit Manoket', 'Artem', 'USER'),
       ('Intolosi Ivono', 'Artem', 'USER'),
       ('Makarovin', 'Artem', 'USER'),
       ('Ulianog Bano', 'Artem', 'USER'),
       ('Bun Dens', 'Artem', 'USER');
INSERT INTO banks(title)
VALUES ('Belarus Bank'),
       ('Prior Bank'),
       ('Alfa Bank'),
       ('BNB Bank'),
       ('Clever Bank');
INSERT INTO accounts(created, interest_date, user_id, currency, amount, bank_id)
VALUES ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 1, 'BYN', 120192.02, 1),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 2, 'BYN', 123.02, 1),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 2, 'BYN', 9999.02, 1),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 2, 'BYN', 777.02, 2),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 2, 'BYN', 1231.02, 3),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 3, 'BYN', 1231.02, 4),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 3, 'BYN', 1231.02, 5),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 4, 'BYN', 1231.02, 1),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 4, 'BYN', 1231.02, 2),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 5, 'BYN', 1231.02, 2),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 5, 'BYN', 1231.02, 5),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 6, 'BYN', 1231.02, 2),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 7, 'BYN', 1231.02, 2),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 8, 'BYN', 1231.02, 2),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 9, 'BYN', 1231.02, 4),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 10, 'BYN', 1231.02, 7),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 11, 'BYN', 1231.02, 2),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 12, 'BYN', 1231.02, 2),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 13, 'BYN', 1231.02, 1),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 14, 'BYN', 1231.02, 2),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 15, 'BYN', 1231.02, 4),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 16, 'BYN', 1231.02, 2),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 17, 'BYN', 1231.02, 2),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 18, 'BYN', 1231.02, 5),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 19, 'BYN', 1231.02, 2),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 20, 'BYN', 1231.02, 2),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 19, 'BYN', 1231.02, 1),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 18, 'BYN', 1231.02, 2),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 17, 'BYN', 1231.02, 2),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 16, 'BYN', 1231.02, 2),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 15, 'BYN', 1231.02, 3),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 14, 'BYN', 1231.02, 2),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 13, 'BYN', 1231.02, 4),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 12, 'BYN', 1231.02, 2),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 11, 'BYN', 1231.02, 2),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 10, 'BYN', 1231.02, 2),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 9, 'BYN', 1231.02, 2),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 8, 'BYN', 1231.02, 1),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 7, 'BYN', 1231.02, 2),
       ('2022-06-16 16:37:23', '2023-07-16 16:37:23', 2, 'BYN', 1231.02, 4);

INSERT INTO transactions(created, currency, amount, sender_bank_id, recipient_bank_id, sender_account_id, recipient_account_id, type, confirmed)
VALUES ('2023-06-16 16:37:23', 'BUN', 10.00, 1, 2, 1, 2, 'DEBITING', '2023-06-16 16:39:40');