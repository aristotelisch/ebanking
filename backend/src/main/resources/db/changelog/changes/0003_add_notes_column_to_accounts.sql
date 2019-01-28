-- liquibase formatted sql

-- changeset telis:1548611124482-16
alter table accounts add column note varchar(255);

-- rollback
alter table accounts drop column note;

