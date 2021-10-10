package br.com.alura.livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.livraria.modelo.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

}
