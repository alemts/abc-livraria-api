package br.com.alura.livraria.modelo;

import java.time.LocalDate;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter @Setter
@ToString(exclude = {"autor"})
@AllArgsConstructor
@NoArgsConstructor
public class Livro {

    private String titulo;
    private LocalDate dataLancamento;
    private int numeroPaginas;
    private String autor;
    
}
