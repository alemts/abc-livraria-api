package br.com.alura.livraria.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alura.livraria.dto.LivroInputDTO;
import br.com.alura.livraria.dto.LivroOutputDTO;
import br.com.alura.livraria.repository.AutorRepository;
import br.com.alura.livraria.repository.LivroRepository;

@ExtendWith(MockitoExtension.class)
class LivroServiceTest {
    
    @Mock
    private LivroRepository livroRepository;
    
    @Mock
    private AutorRepository autorRepository;

    @InjectMocks
    private LivroService service;
    
    @Test
    void deveriaCadastrarUmLivro() {
        LivroInputDTO inputDto = new LivroInputDTO(
                "O Muro de Berlim",
                LocalDate.now(),
                120,
                2L
            );
        
        
        LivroOutputDTO outputDto = service.cadastrar(inputDto);
        
        assertEquals(outputDto.getTitulo(), inputDto.getTitulo());
        assertEquals(outputDto.getDataLancamento(), inputDto.getDataLancamento());
        assertEquals(outputDto.getNumeroPaginas(),  inputDto.getNumeroPaginas());
    }

    @Test
    void naoDeveriaCadastrarUmLivroComAutorInexistente() {
        LivroInputDTO inputDto = new LivroInputDTO(
                "O Muro de Berlim",
                LocalDate.now(),
                120,
                9999L
            );
        
        Mockito
        .when(autorRepository.getById(inputDto.getAutorId()))
        .thenThrow(EntityNotFoundException.class);
        
        assertThrows(
                IllegalArgumentException.class,
                () -> service.cadastrar(inputDto));
                
                
        
    }
}
