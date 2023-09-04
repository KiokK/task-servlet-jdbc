DROP SCHEMA IF EXISTS public CASCADE;
CREATE SCHEMA public;

CREATE TABLE users
(
    id       bigserial PRIMARY KEY NOT NULL,
    username varchar(30) UNIQUE    NOT NULL,
    role     varchar(7) DEFAULT 'USER',
    password varchar(30)           NOT NULL
);
CREATE TABLE banks
(
    id    bigserial PRIMARY KEY NOT NULL,
    title varchar(30)           NOT NULL
);
CREATE TABLE accounts
(
    id            bigserial PRIMARY KEY NOT NULL,
    created       timestamp             NOT NULL DEFAULT CURRENT_TIMESTAMP,
    user_id       bigint                NOT NULL references users (id),
    currency      varchar(3)            NOT NULL,
    amount        numeric(19, 2)        NOT NULL check ( amount >= 0 ),
    interest_date timestamp             NOT NULL DEFAULT CURRENT_TIMESTAMP,
    bank_id       bigserial             NOT NULL references banks (id)
);

CREATE TABLE transactions
(
    id                   bigserial PRIMARY KEY NOT NULL,
    created              timestamp             NOT NULL,
    currency             varchar(3)            NOT NULL,
    amount               numeric(19, 2)        NOT NULL check ( amount >= 0 ),
    sender_bank_id       bigserial             NOT NULL references banks (id),
    recipient_bank_id    bigserial             NOT NULL references banks (id),
    sender_account_id    bigint                NOT NULL references accounts (id),
    recipient_account_id bigint                NOT NULL references accounts (id),
    type                 varchar(20)           NOT NULL,
    confirmed            timestamp
);

