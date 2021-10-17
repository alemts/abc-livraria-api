package br.com.alura.livraria.service;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.livraria.dto.LivroInputDTO;
import br.com.alura.livraria.dto.LivroOutputDTO;
import br.com.alura.livraria.modelo.Autor;
import br.com.alura.livraria.modelo.Livro;
import br.com.alura.livraria.repository.AutorRepository;
import br.com.alura.livraria.repository.LivroRepository;

@Service
public class LivroService {
    
    @Autowired
    private LivroRepository livroRepository;
    
    @Autowired
    private AutorRepository autorRepository;
    
    private ModelMapper modelMapper = new ModelMapper();

    public Page<LivroOutputDTO> listar(Pageable paginacao) {
        return livroRepository
                .findAll(paginacao)
                .map(l -> modelMapper.map(l, LivroOutputDTO.class));
    }

    @Transactional
    public LivroOutputDTO cadastrar(@Valid LivroInputDTO dto) {
        Livro livro = modelMapper.map(dto, Livro.class);
        Autor autor = autorRepository.getById(dto.getAutorId());

        livro.setId(null);
        livro.setAutor(autor);
        livroRepository.save(livro);

        return modelMapper.map(livro, LivroOutputDTO.class);        
    }

}
