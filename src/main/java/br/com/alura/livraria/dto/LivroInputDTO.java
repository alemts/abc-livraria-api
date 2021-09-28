package br.com.alura.livraria.dto;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LivroInputDTO {
    
    @NotNull @NotEmpty
    @Size(min=5)
    private String titulo;
    
    @PastOrPresent
    @NotNull
    private LocalDate dataLancamento;
    
    @Min(100)
    @NotNull
    private int numeroPaginas;
    
    private String autor;
}
