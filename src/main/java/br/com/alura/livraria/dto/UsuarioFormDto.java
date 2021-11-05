package br.com.alura.livraria.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class UsuarioFormDto
{
    @NotBlank(message = "Campo Nome deve ser informado")
    private String nome;
    
    @NotBlank
    private String login;
}
