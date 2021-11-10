package br.com.alura.livraria.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import br.com.alura.livraria.dto.LivroInputDTO;
import br.com.alura.livraria.dto.LivroOutputDTO;
import br.com.alura.livraria.modelo.Autor;
import br.com.alura.livraria.modelo.Livro;
import br.com.alura.livraria.modelo.Usuario;
import br.com.alura.livraria.repository.AutorRepository;
import br.com.alura.livraria.repository.LivroRepository;

@ExtendWith(MockitoExtension.class)
class LivroServiceTest {
    
    @Mock
    private LivroRepository livroRepository;
    
    @Mock
    private AutorRepository autorRepository;
    
    @Mock
    private ModelMapper modelMapper;
    
    @InjectMocks
    private LivroService service;

    private Usuario logado;
    private Autor autor;
    
    @BeforeEach
    public void before() {
        this.logado = new Usuario("Alexandre", "ale", "123");
        this.autor = new Autor("Garen Mcwell", "garen", LocalDate.now(), "mini CV");
    }
    
    private LivroInputDTO criarLivroInputDTO() {
        LivroInputDTO inputDto = new LivroInputDTO(
                "O Muro de Berlim",
                LocalDate.now(),
                120,
                2L,
                1L
            );
        return inputDto;
    }
    
    @Test
    void deveriaCadastrarUmLivro() {
        LivroInputDTO inputDto = criarLivroInputDTO();

        // Simulando cena com autor e usuario
        Mockito
        .when(autorRepository.getById(inputDto.getAutorId()))
        .thenReturn(autor);

        Livro livro = new Livro(
                inputDto.getTitulo(), 
                inputDto.getDataLancamento(),
                inputDto.getNumeroPaginas(),
                autor,
                logado);

        Mockito
        .when(modelMapper.map(inputDto, Livro.class))
        .thenReturn(livro);
        
        Mockito
        .when(modelMapper.map(livro, LivroOutputDTO.class))
        .thenReturn(new LivroOutputDTO(
                null,
                livro.getTitulo(),
                livro.getDataLancamento(),
                livro.getNumeroPaginas()));
        
        LivroOutputDTO outputDto = service.cadastrar(inputDto, logado);
        
        Mockito.verify(livroRepository).save(Mockito.any());
        
        assertEquals(outputDto.getTitulo(), inputDto.getTitulo());
        assertEquals(outputDto.getDataLancamento(), inputDto.getDataLancamento());
        assertEquals(outputDto.getNumeroPaginas(),  inputDto.getNumeroPaginas());
    }

    @Test
    void naoDeveriaCadastrarUmLivroComAutorInexistente() {
        LivroInputDTO inputDto = criarLivroInputDTO();
        
        Mockito
        .when(autorRepository.getById(inputDto.getAutorId()))
        .thenThrow(EntityNotFoundException.class);
        
        assertThrows(
                IllegalArgumentException.class,
                () -> service.cadastrar(inputDto, logado));
    }
}
