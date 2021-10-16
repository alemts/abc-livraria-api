package br.com.alura.livraria.service;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.livraria.dto.LivroInputDTO;
import br.com.alura.livraria.dto.LivroOutputDTO;
import br.com.alura.livraria.modelo.Livro;
import br.com.alura.livraria.repository.LivroRepository;

@Service
public class LivroService {
    
    @Autowired
    private LivroRepository livroRepository;
    private ModelMapper modelMapper = new ModelMapper();

    public Page<LivroOutputDTO> listar(Pageable paginacao) {
        Page<Livro> livros = livroRepository.findAll(paginacao);
        return livros
                .map(t -> modelMapper.map(t, LivroOutputDTO.class));
    }

    @Transactional
    public LivroOutputDTO cadastrar(@Valid LivroInputDTO dto) {
        Livro livro = new ModelMapper().map(dto, Livro.class);
        livro.setId(null);

        livroRepository.save(livro);
        return modelMapper.map(livro, LivroOutputDTO.class);
    }

}
