package br.com.alura.livraria.modelo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(name = "Data_Lancamento")
    private LocalDate dataLancamento;

    @Column(name = "Numero_Paginas")
    private Integer numeroPaginas;

    @ManyToOne
    @JoinColumn(name = "autor_id") // esse ja eh o nome default
    private Autor autor;
    
    //Sem @JoinColumn o nome da coluna fica no padrao: usuario_id
    @ManyToOne
    private Usuario usuario;

    public Livro(String titulo, LocalDate dataLancamento, Integer numeroPaginas,
            Autor autor, Usuario usuario) {
        this.titulo = titulo;
        this.dataLancamento = dataLancamento;
        this.numeroPaginas = numeroPaginas;
        this.autor = autor;
        this.usuario = usuario;
    }

    public void atualizarInformacoes(String titulo, LocalDate dataLancamento, Integer numeroPaginas) {
        this.titulo = titulo;
        this.dataLancamento = dataLancamento;
        this.numeroPaginas = numeroPaginas;
    }


}
