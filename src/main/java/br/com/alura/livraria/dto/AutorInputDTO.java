package br.com.alura.livraria.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AutorInputDTO {

    @NotEmpty
    @NotNull
    private String nome;

    @NotBlank
    private String email;

    //@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @PastOrPresent
    private LocalDate dataNascimento;

    @NotEmpty
    private String miniCurriculo;

}
