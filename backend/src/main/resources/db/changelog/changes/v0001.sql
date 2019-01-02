create table "user" (
  id integer not null,
  firstName varchar(50) not null,
  lastName varchar(50) not null,
  username varchar(50) not null unique,
  password varchar(80) not null,
  primary key (id)
);