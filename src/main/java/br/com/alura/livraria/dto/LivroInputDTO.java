package br.com.alura.livraria.dto;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LivroInputDTO {
    
    @NotBlank                           // Juncao do NotNull + NotEmpty
    @Size(min=10)
    private String titulo;              // Titulo deve ser obrigatorio e ter no minimo 10 caracteres;

    @NotNull
    @PastOrPresent
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataLancamento;   // Data de lancamento deve ser uma data menor ou igual a data atual;
    
    @Positive
    @Min(100)
    @NotNull
    private Integer numeroPaginas;      // Numero de paginas deve ser maior ou igual a 100.

    //private AutorInputDTO autor;
    @JsonAlias("autor_id")
    private Long autorId;
}
