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

import br.com.alura.livraria.dto.AutorInputDTO;
import br.com.alura.livraria.dto.AutorOutputDTO;
import br.com.alura.livraria.service.AutorService;

@RestController
@RequestMapping("/autores")
public class AutorController
{
	@Autowired
	private AutorService service;

	@GetMapping
	public Page<AutorOutputDTO> listar(@PageableDefault(size=20) Pageable paginacao) {

		return service.listar(paginacao);

	}
	
    @PostMapping
    public ResponseEntity<AutorOutputDTO> cadastrar(@RequestBody @Valid AutorInputDTO dto,
            UriComponentsBuilder uriBuilder) {
        AutorOutputDTO autorOutputDTO = service.cadastrar(dto);
        
        URI location = uriBuilder
                .path("/autores/{id}")
                .buildAndExpand(autorOutputDTO.getId())
                .toUri();
        
        return ResponseEntity.created(location).body(autorOutputDTO);
    }

}
