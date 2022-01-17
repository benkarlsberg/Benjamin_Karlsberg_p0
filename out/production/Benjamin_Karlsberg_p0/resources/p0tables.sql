--drop table if exists users;
--drop table if exists accounts;

--create users table
create table if not exists users (
u_id serial primary key, 
first_name varchar(50) not null,
last_name varchar(50) not null,
username varchar(50) not null,
pw varchar(50) not null);

-- create accounts table
create table if not exists accounts (
acc_id serial primary key,
u_id int,
account_type varchar(50) not null,
balance numeric(12,2),
foreign key (u_id) references users(u_id) on delete set null
);

select * from users;

select * from accounts;

