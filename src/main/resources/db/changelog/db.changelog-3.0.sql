--liquibase formatted sql

--changeset artem:1
ALTER TABLE users
ADD COLUMN image VARCHAR(64);

--changeset artem:2
ALTER TABLE users_aud
ADD COLUMN image VARCHAR(64);
