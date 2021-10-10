create table autores(
  id bigint not null auto_increment,
  nome varchar(100) not null,
  email varchar(100) not null,
  dataNascimento date not null,
  miniCV int not null,
  primary key(id)
);
