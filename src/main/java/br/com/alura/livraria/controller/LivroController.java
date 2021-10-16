package br.com.alura.livraria.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.livraria.dto.LivroInputDTO;
import br.com.alura.livraria.dto.LivroOutputDTO;
import br.com.alura.livraria.service.LivroService;

@RestController
@RequestMapping("/livros")

public class LivroController {

    @Autowired
    private LivroService service;

    @GetMapping
    public Page<LivroOutputDTO> listar(@PageableDefault(size=10) Pageable paginacao) {
        return service.listar(paginacao);
    }

    @PostMapping
    public ResponseEntity<LivroOutputDTO> cadastrar(@RequestBody @Valid LivroInputDTO dto,
            UriComponentsBuilder uriBuilder) {
        LivroOutputDTO livroOutputDTO = service.cadastrar(dto);
        
        URI location = uriBuilder
                .path("/livros/{id}")
                .buildAndExpand(livroOutputDTO.getId())
                .toUri();

        return ResponseEntity.created(location).body(livroOutputDTO);
    }
}
