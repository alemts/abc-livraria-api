package br.com.alura.livraria.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.livraria.dto.AtualizacaoLivroInputDTO;
import br.com.alura.livraria.dto.LivroInputDTO;
import br.com.alura.livraria.dto.LivroOutputDTO;
import br.com.alura.livraria.modelo.Usuario;
import br.com.alura.livraria.service.LivroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/livros")
@Api(tags = "Livro")
public class LivroController {

    @Autowired
    private LivroService service;

    @GetMapping
    @ApiOperation("Listar livros")
    public Page<LivroOutputDTO> listar(
            @PageableDefault(size=10) Pageable paginacao,
            @AuthenticationPrincipal Usuario logado) {
        return service.listar(paginacao, logado);
    }

    @PostMapping
    @ApiOperation("Cadastrar livro")
    public ResponseEntity<LivroOutputDTO> cadastrar(
            @RequestBody @Valid LivroInputDTO dto,
            UriComponentsBuilder uriBuilder,
            @AuthenticationPrincipal Usuario logado) {
        LivroOutputDTO livroOutputDTO = service.cadastrar(dto, logado);
        
        URI location = uriBuilder
                .path("/livros/{id}")
                .buildAndExpand(livroOutputDTO.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(livroOutputDTO);
    }
    
    @GetMapping("/lancamentos")
    public List<LivroOutputDTO> ultimasPublicacoesDoAutor(
            String nome, LocalDate data) {
        return service.ultimasPublicacoesDoAutor(nome,data);
    }
    
    @PutMapping
    @ApiOperation("Atualizar livro")
    public ResponseEntity<LivroOutputDTO> atualizar(
            @RequestBody @Valid AtualizacaoLivroInputDTO dto,
            @AuthenticationPrincipal Usuario logado) {
        LivroOutputDTO atualizado = service.atualizar(dto, logado);
        return ResponseEntity.ok(atualizado);
    }
    
    @DeleteMapping("/{id}")
    @ApiOperation("Excluir livro")
    public ResponseEntity<LivroOutputDTO> excluir(
            @PathVariable @NotNull Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @ApiOperation("Detalhar livro")
    public ResponseEntity<LivroOutputDTO> detalhar(
            @PathVariable @NotNull Long id,
            @AuthenticationPrincipal Usuario logado) {
        LivroOutputDTO dto = service.detalhar(id);
        return ResponseEntity.ok(dto);
    }
    
}
