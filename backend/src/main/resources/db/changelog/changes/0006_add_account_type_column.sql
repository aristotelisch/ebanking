--liquibase formatted sql

--changeset telis:1548611124483-20
alter table accounts add column type varchar(255) not null default 'SAVINGS';

