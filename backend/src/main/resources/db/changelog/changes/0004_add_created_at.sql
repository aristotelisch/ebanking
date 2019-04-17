--liquibase formatted sql

--changeset telis:1548611124482-17
alter table accounts add column created_at timestamp not null Default now();

--changeset telis:1548611124482-18
alter table accounts add column updated_at timestamp not null Default now();
