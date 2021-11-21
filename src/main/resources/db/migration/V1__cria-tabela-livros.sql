create table livros(
  Id bigint not null auto_increment,
  Titulo varchar(100) not null,
  Data_Lancamento date not null,
  Numero_Paginas int not null,
  primary key(id)
);