package br.com.alura.livraria.modelo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "autores")
public class Autor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    
    private String email;
    
    @Column(name = "Data_Nascimento")
    private LocalDate dataNascimento;

    @Column(name = "Mini_CV")
    private String miniCurriculo;

    public Autor(String nome, String email, LocalDate dataNascimento, String miniCurriculo) {
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.miniCurriculo = miniCurriculo;
    }

    public void atualizarInformacoes(
            String nome, String email, LocalDate dataNascimento, String miniCurriculo) {
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.miniCurriculo = miniCurriculo;
    }

}
