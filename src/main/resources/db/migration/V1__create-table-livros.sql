create table livros(
  id bigint not null auto_increment,
  titulo varchar(100) not null,
  dataLancamento date not null,
  numeroPaginas int not null,
  primary key(id)
);
