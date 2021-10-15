alter table livros
  add column Autor_Id bigint not null;
  
alter table livros
  add foreign key(Autor_Id) references autores(Id);
