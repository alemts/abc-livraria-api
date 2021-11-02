package br.com.alura.livraria.controller;

import java.net.URI;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.livraria.dto.AtualizacaoAutorInputDTO;
import br.com.alura.livraria.dto.AutorInputDTO;
import br.com.alura.livraria.dto.AutorOutputDTO;
import br.com.alura.livraria.service.AutorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/autores")
@Api(tags = "Autor")
public class AutorController
{
	@Autowired
	private AutorService service;

	@GetMapping
	@ApiOperation("Listar autores")
	public Page<AutorOutputDTO> listar(@PageableDefault(size=20) Pageable paginacao) {

		return service.listar(paginacao);

	}
	
    @PostMapping
    @ApiOperation("Cadastrar autor")
    public ResponseEntity<AutorOutputDTO> cadastrar(@RequestBody @Valid AutorInputDTO dto,
            UriComponentsBuilder uriBuilder) {
        AutorOutputDTO autorOutputDTO = service.cadastrar(dto);
        
        URI location = uriBuilder
                .path("/autores/{id}")
                .buildAndExpand(autorOutputDTO.getId())
                .toUri();
        
        return ResponseEntity.created(location).body(autorOutputDTO);
    }
    
    @PutMapping
    @ApiOperation("Atualizar autor")
    public ResponseEntity<AutorOutputDTO> atualizar(
            @RequestBody @Valid AtualizacaoAutorInputDTO dto) {
        AutorOutputDTO atualizado = service.atualizar(dto);
        return ResponseEntity.ok(atualizado);
    }
    
    @DeleteMapping("/{id}")
    @ApiOperation("Excluir autor")
    public ResponseEntity<AutorOutputDTO> excluir(@PathVariable @NotNull Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @ApiOperation("Detalhar autor")
    public ResponseEntity<AutorOutputDTO> detalhar(@PathVariable @NotNull Long id) {
        AutorOutputDTO dto = service.detalhar(id);
        return ResponseEntity.ok(dto);
    }
    

}
