package br.com.alura.livraria.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.livraria.modelo.Autor;

@RestController
@RequestMapping("/autores")
public class AutorController
{

    private List<Autor> autores = new ArrayList<>();

    @GetMapping
    public List<Autor> listar() {
        return autores;
    }

    @PostMapping
    public void cadastrar(@RequestBody Autor autor) {
        autores.add(autor);
    }

}
