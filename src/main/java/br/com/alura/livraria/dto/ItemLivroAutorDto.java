package br.com.alura.livraria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemLivroAutorDto {
    
    private String nomeAutor;
    private Long quantidadeLivrosPorAutor;
    private Double percentualLivrosPorAutor; 

}
