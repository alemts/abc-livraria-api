package br.com.alura.livraria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.livraria.dto.ItemLivroAutorDto;
import br.com.alura.livraria.service.RelatorioService;

@RestController
@RequestMapping("/relatorios")
public class RelatoriosController {

    @Autowired
    private RelatorioService service;
    
    @GetMapping("/qtdlivrospublicados")
    public List<ItemLivroAutorDto> relatorioQuantidadeLivrosPublicados() {
        return service.relatorioQuantidadeLivrosPublicados();
    }

}
