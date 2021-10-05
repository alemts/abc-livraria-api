package br.com.alura.livraria.dto;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class LivroInputDTO {
    
    @NotNull @NotEmpty
    @Size(min=10)
    private String titulo;              // Titulo deve ser obrigatorio e ter no minimo 10 caracteres;

    @NotBlank                           // Juncao do NotNull + NMotEmpty
    @PastOrPresent
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataLancamento;   // Data de lancamento deve ser uma data menor ou igual a data atual;
    
    @Positive
    @Min(100)
    @NotNull
    private int numeroPaginas;          // Numero de paginas deve ser maior ou igual a 100.

    private AutorInputDTO autor;
}
