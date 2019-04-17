--liquibase formatted sql

--changeset telis:1548611124482-15
INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');