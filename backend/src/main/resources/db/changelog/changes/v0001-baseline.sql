--liquibase formatted sql

--changeset telis:1546564045494-1
CREATE SEQUENCE hibernate_sequence;

--changeset telis:1546564045494-2
CREATE TABLE roles (id BIGSERIAL NOT NULL, name VARCHAR(60), CONSTRAINT roles_pkey PRIMARY KEY (id));

--changeset telis:1546564045494-3
CREATE TABLE user_roles (user_id BIGINT NOT NULL, role_id BIGINT NOT NULL, CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id));

--changeset telis:1546564045494-4
CREATE TABLE users (id BIGINT NOT NULL, email VARCHAR(255), first_name VARCHAR(255), last_name VARCHAR(255), password VARCHAR(100), username VARCHAR(255), CONSTRAINT users_pkey PRIMARY KEY (id));

--changeset telis:1546564045494-5
ALTER TABLE roles ADD CONSTRAINT uk_nb4h0p6txrmfc0xbrd1kglp9t UNIQUE (name);

--changeset telis:1546564045494-6
ALTER TABLE users ADD CONSTRAINT uk_r43af9ap4edm43mmtq01oddj6 UNIQUE (username);

--changeset telis:1546564045494-7
ALTER TABLE users ADD CONSTRAINT uk_sx468g52bpetvlad2j9y0lptc UNIQUE (email);

--changeset telis:1546564045494-8
ALTER TABLE user_roles ADD CONSTRAINT fkh8ciramu9cc9q3qcqiv4ue8a6 FOREIGN KEY (role_id) REFERENCES roles (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

--changeset telis:1546564045494-9
ALTER TABLE user_roles ADD CONSTRAINT fkhfh9dx7w3ubf1co1vdev94g3f FOREIGN KEY (user_id) REFERENCES users (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

