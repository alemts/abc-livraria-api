package br.com.alura.livraria.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<LivroOutputDTO> listar() {
        List<Livro> livros = livroRepository.findAll();
        return livros
                .stream()
                .map(t -> modelMapper.map(t, LivroOutputDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public void cadastrar(@Valid LivroInputDTO dto) {
        Livro livro = new ModelMapper().map(dto, Livro.class);
        livro.setId(null);

        livroRepository.save(livro);
    }

}
