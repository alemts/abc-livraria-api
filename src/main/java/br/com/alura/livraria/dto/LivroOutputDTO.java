package br.com.alura.livraria.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroOutputDTO {
    
    private Long id;
    
    private String titulo;
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataLancamento;
    
    private Integer numeroPaginas;
    
    //private AutorOutputDTO autor;
}
