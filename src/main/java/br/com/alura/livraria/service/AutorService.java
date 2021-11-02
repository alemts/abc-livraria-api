package br.com.alura.livraria.service;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.livraria.dto.AtualizacaoAutorInputDTO;
import br.com.alura.livraria.dto.AutorDetalhadoOutputDTO;
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
        return autorRepository
                .findAll(paginacao)
                .map(a -> modelMapper.map(a, AutorOutputDTO.class));        
    }

    @Transactional
    public AutorOutputDTO cadastrar(@Valid AutorInputDTO dto) {
        Autor autor = new ModelMapper().map(dto, Autor.class);
        
        autorRepository.save(autor);

        return modelMapper.map(autor, AutorOutputDTO.class);
    }

    @Transactional
    public AutorOutputDTO atualizar(AtualizacaoAutorInputDTO dto) {
        Autor autor = autorRepository.getById(dto.getId());
        
        autor.atualizarInformacoes(
                dto.getNome(), 
                dto.getEmail(), 
                dto.getDataNascimento(),
                dto.getMiniCurriculo());
        
        return modelMapper.map(autor, AutorOutputDTO.class);
    }

    @Transactional
    public void excluir(Long id) {
        autorRepository.deleteById(id);
    }

    public AutorDetalhadoOutputDTO detalhar(Long id) {
        Autor autor = autorRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        return modelMapper.map(autor, AutorDetalhadoOutputDTO.class);
    }
    
}
