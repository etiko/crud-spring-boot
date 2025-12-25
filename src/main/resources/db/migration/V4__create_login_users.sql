create table login
(
    id       bigint auto_increment
        primary key,
    email    varchar(255) not null,
    password varchar(255) not null
);