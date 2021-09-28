package br.com.alura.livraria.dto;

import java.time.LocalDate;

import br.com.alura.livraria.modelo.Livro;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LivroOutputDTO {
    private String titulo;
    private LocalDate dataLancamento;
    private int numeroPaginas;
    
//    public LivroOutputDTO(Livro livro) {
//        this.titulo = livro.getTitulo();
//        this.dataLancamento = livro.getDataLancamento();
//        this.numeroPaginas = livro.getNumeroPaginas();
//    }
    
}
