package br.com.alura.livraria.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroDetalhadoOutputDTO extends LivroOutputDTO {
    
    private AutorOutputDTO autor;
    
    private UsuarioDto usuario;

}
