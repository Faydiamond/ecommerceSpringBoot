create database bd_apirest_prueba;
use bd_apirest_prueba;

create table cliente(
id int not null primary key auto_increment,
nombre varchar(80) not null,
apellido varchar(80) not null,
email varchar(80) not null
);

insert into cliente(nombre, apellido, email) values 
("Jhon","Smith","jhonsmith@email.com");

select * from cliente;





