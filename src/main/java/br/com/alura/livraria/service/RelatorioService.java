package br.com.alura.livraria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.livraria.dto.ItemLivroAutorDto;
import br.com.alura.livraria.repository.LivroRepository;

@Service
public class RelatorioService {

    @Autowired
    private LivroRepository repository;
    
    public List<ItemLivroAutorDto> relatorioQuantidadeLivrosPublicados() {
        return repository.relatorioQuantidadeLivrosPublicados();
    }

}
