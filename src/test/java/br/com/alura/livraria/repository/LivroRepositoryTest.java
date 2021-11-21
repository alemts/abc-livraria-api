package br.com.alura.livraria.repository;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.alura.livraria.dto.ItemLivroAutorDto;
import br.com.alura.livraria.modelo.Autor;
import br.com.alura.livraria.modelo.Livro;
import br.com.alura.livraria.modelo.Usuario;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
class LivroRepositoryTest {

    @Autowired
    private LivroRepository repository;
    
    @Autowired
    private TestEntityManager em;
    
    @Test
    void deveriaRetornarRelatorioQuantidadeLivrosPublicados() {
        /*
        Livros:
        3   Aprenda Java            2005-10-15  120 2
        4   Como ser mais produtivo 2005-10-15  120 3
        5   Aprenda a falar em pub  2005-10-15  120 4
        6   Otimizando seu tempo    2005-10-15  120 3
        7   Como fazer bolos incriv 2005-10-15  120 5
        
        Autores:
        2   Andre       andre@gmail.com 2004-03-25  bla bla bla
        3   Fernanda    fe@gmail.com    2004-03-25  bla bla bla
        4   Juliana     ju@gmail.com    2004-03-25  bla bla bla
        5   Rita        rita@gmail.com  2004-03-25  bla bla bla
        
        Relatorio Qtd Livros Publicados por Autor:
        Andre       1   0.2000
        Fernanda    2   0.4000
        Juliana     1   0.2000
        Rita        1   0.2000                            
        */
        // Quatro autores
        /*Autor andre = new Autor(
                "Andre", 
                "email@email.com",
                LocalDate.now(),
                "CV ..."
            );
        em.persist(andre);
        
        Autor fernanda = new Autor(
                "Fernanda", 
                "email@email.com",
                LocalDate.now(),
                "CV ..."
            );
        em.persist(fernanda);*/
        
        Autor juliana = new Autor(
                "Juliana", 
                "email@email.com",
                LocalDate.now(),
                "CV ..."
            );
        em.persist(juliana);
        
        Autor rita = new Autor(
                "Rita", 
                "email@email.com",
                LocalDate.now(),
                "CV ..."
            );
        em.persist(rita);

        //Criando um usuario qualquer dado nao eh importante para este teste
        Usuario usuario = new Usuario("Alexandre", "ale@email.com", "123");
        em.persist(usuario);
        
        // Cinco livros dos quatro autores
        Livro livro1 = new Livro(
                "Livro",
                LocalDate.now(),
                120,
                juliana,
                usuario
            );
        em.persist(livro1);

        Livro livro2 = new Livro(
                "Livro",
                LocalDate.now(),
                120,
                juliana,
                usuario
            );
        em.persist(livro2);
        
        Livro livro3 = new Livro(
                "Livro",
                LocalDate.now(),
                120,
                juliana,
                usuario
            );
        em.persist(livro3);
        
        Livro livro4 = new Livro(
                "Livro",
                LocalDate.now(),
                120,
                rita,
                usuario
            );
        em.persist(livro4);
        
        Livro livro5 = new Livro(
                "Livro",
                LocalDate.now(),
                120,
                rita,
                usuario
            );
        em.persist(livro5);

        List<ItemLivroAutorDto> relatorio = repository.relatorioQuantidadeLivrosPublicados();
        Assertions
        .assertThat(relatorio)
        .hasSize(2)
        .extracting(
                ItemLivroAutorDto::getNomeAutor,
                ItemLivroAutorDto::getQuantidadeLivrosPorAutor,
                ItemLivroAutorDto::getPercentualLivrosPorAutor)
        .containsExactlyInAnyOrder(
                Assertions.tuple("Juliana", 3L, 0.0),
                Assertions.tuple("Rita",    2L, 0.0)
                );
    }

}
