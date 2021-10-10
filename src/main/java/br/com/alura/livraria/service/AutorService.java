package br.com.alura.livraria.service;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.livraria.dto.AutorInputDTO;
import br.com.alura.livraria.dto.AutorOutputDTO;
import br.com.alura.livraria.modelo.Autor;
import br.com.alura.livraria.repository.AutorRepository;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;
    private ModelMapper modelMapper = new ModelMapper();

    public Page<AutorOutputDTO> listar(Pageable paginacao) {
        Page<Autor> autores = autorRepository.findAll(paginacao);
        return autores
                .map(t -> modelMapper.map(t, AutorOutputDTO.class));
    }

    @Transactional
    public void cadastrar(@Valid AutorInputDTO dto) {
        Autor autor = new ModelMapper().map(dto, Autor.class);
        autorRepository.save(autor);

    }

}
