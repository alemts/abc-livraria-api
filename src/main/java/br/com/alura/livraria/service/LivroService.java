package br.com.alura.livraria.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.alura.livraria.dto.LivroInputDTO;
import br.com.alura.livraria.dto.LivroOutputDTO;
import br.com.alura.livraria.modelo.Livro;

@Service
public class LivroService {
    private List<Livro> livros = new ArrayList<>();
    private ModelMapper modelMapper = new ModelMapper();

    public List<LivroOutputDTO> listar() {
        return livros
                .stream()
                .map(t -> modelMapper.map(t, LivroOutputDTO.class))
                .collect(Collectors.toList());
    }

    public void cadastrar(@Valid LivroInputDTO dto) {
        Livro livro = new ModelMapper().map(dto, Livro.class);

        livros.add(livro);
    }

}
