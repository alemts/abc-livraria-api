package br.com.alura.livraria.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alura.livraria.dto.ItemLivroAutorDto;
import br.com.alura.livraria.modelo.Livro;
import br.com.alura.livraria.modelo.Usuario;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    final String jpaAbreConstrutor = "new br.com.alura.livraria.dto.ItemLivroAutorDto ( ";
    final String jpaFechaConstrutor = " ) ";
    
    @Query(
           "    SELECT                                                      " +
           jpaAbreConstrutor                                                  +
           "           au.nome,                                             " + //NomeAutor 
           "           count(li),                                           " + //QuantidadeLivrosPorAutor
           "           count(li) / (SELECT count(li2) FROM Livro li2) * 1.0 " + //PercentualLivrosPorAutor
           jpaFechaConstrutor                                                 +
           "      FROM Livro li                                             " + 
           "INNER JOIN Autor au ON au.id = li.autor                         " + 
           "  GROUP BY au.nome "
          )
    List<ItemLivroAutorDto> relatorioQuantidadeLivrosPublicados();

    @Query(
           "SELECT l                        " +
           " FROM Livro l                   " +
           " JOIN l.autor a                 " +
           "WHERE a.nome            = :nome " +
           "  AND l.dataLancamento >= :data "
          )
    List<Livro> ultimasPublicacoesDoAutor(String nome, LocalDate data);

    Page<Livro> findAllByUsuario(Pageable paginacao, Usuario usuario);
    
}
