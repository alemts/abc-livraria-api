package br.com.alura.livraria.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.livraria.dto.LivroInputDTO;
import br.com.alura.livraria.dto.LivroOutputDTO;
import br.com.alura.livraria.service.LivroService;

@RestController
@RequestMapping("/livros")

public class LivroController {

    @Autowired
    private LivroService service;

    @GetMapping
    public List<LivroOutputDTO> listar() {
        return service.listar();
    }

    @PostMapping
    public void cadastrar(@RequestBody @Valid LivroInputDTO dto) {
        service.cadastrar(dto);
    }
}
