package br.com.alura.livraria.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.livraria.dto.AtualizacaoLivroInputDTO;
import br.com.alura.livraria.dto.LivroDetalhadoOutputDTO;
import br.com.alura.livraria.dto.LivroInputDTO;
import br.com.alura.livraria.dto.LivroOutputDTO;
import br.com.alura.livraria.modelo.Autor;
import br.com.alura.livraria.modelo.Livro;
import br.com.alura.livraria.modelo.Usuario;
import br.com.alura.livraria.repository.AutorRepository;
import br.com.alura.livraria.repository.LivroRepository;

@Service
public class LivroService {
    
    @Autowired
    private LivroRepository livroRepository;
    
    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<LivroOutputDTO> listar(Pageable paginacao, Usuario logado) {
        return livroRepository
                .findAllByUsuario(paginacao, logado)
                .map(l -> modelMapper.map(l, LivroOutputDTO.class));
    }

    @Transactional
    public LivroOutputDTO cadastrar(@Valid LivroInputDTO dto, Usuario logado) {
        try {
            Autor autor = autorRepository.getById(dto.getAutorId());
            Livro livro = modelMapper.map(dto, Livro.class);
            livro.setId(null);
            livro.setAutor(autor);
            livro.setUsuario(logado);

            livroRepository.save(livro);

            return modelMapper.map(livro, LivroOutputDTO.class);        
        }
        catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("autor inexistente");
        }
    }
    
    public List<LivroOutputDTO> ultimasPublicacoesDoAutor(String nome, LocalDate data) {
        return livroRepository
                .ultimasPublicacoesDoAutor(nome, data)
                .stream()
                .map(l -> modelMapper.map(l, LivroOutputDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public LivroOutputDTO atualizar(AtualizacaoLivroInputDTO dto, Usuario logado) {
        Livro livro = livroRepository.getById(dto.getId());
        livro.setUsuario(logado);
        
        livro.atualizarInformacoes(
                dto.getTitulo(), 
                dto.getDataLancamento(), 
                dto.getNumeroPaginas());
        
        return modelMapper.map(livro, LivroOutputDTO.class);
    }

    @Transactional
    public void excluir(Long id) {
        livroRepository.deleteById(id);
    }
    
    public LivroDetalhadoOutputDTO detalhar(Long id) {
        Livro livro = livroRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        return modelMapper.map(livro, LivroDetalhadoOutputDTO.class);
    }
}
