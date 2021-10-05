package br.com.alura.livraria.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.alura.livraria.dto.AutorInputDTO;
import br.com.alura.livraria.dto.AutorOutputDTO;
import br.com.alura.livraria.modelo.Autor;

@Service
public class AutorService {

    private List<Autor> autores = new ArrayList<>();
    private ModelMapper modelMapper = new ModelMapper();

    public List<AutorOutputDTO> listar() {

        return autores.stream()
                .map(t -> modelMapper.map(t, AutorOutputDTO.class))
                .collect(Collectors.toList());
    }

    public void cadastrar(@Valid AutorInputDTO dto) {
        Autor Autor = new ModelMapper().map(dto, Autor.class);

        autores.add(Autor);

    }

}
