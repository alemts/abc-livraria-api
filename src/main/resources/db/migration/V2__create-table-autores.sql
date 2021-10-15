create table autores(
  Id bigint not null auto_increment,
  Nome varchar(100) not null,
  Email varchar(100) not null,
  Data_Nascimento date not null,
  Mini_CV varchar(250) not null,
  primary key(id)
);
