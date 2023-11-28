create schema if not exists users;

create table users.user(
    id bigserial primary key,
    name varchar(100) not null,
    document varchar(100) not null,
    address varchar(100) not null,
    email varchar(100) not null,
    phone varchar(100) not null,
    date_register varchar(100) not null
)

