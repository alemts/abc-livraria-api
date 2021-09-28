package br.com.alura.livraria.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.livraria.dto.LivroInputDTO;
import br.com.alura.livraria.dto.LivroOutputDTO;
import br.com.alura.livraria.modelo.Livro;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private List<Livro> listLivros = new ArrayList<>();
    
    private ModelMapper modelMapper = new ModelMapper();    

    @GetMapping
    public List<LivroOutputDTO> listar() {
//        List<LivroOutputDTO> listLivrosDTO = new ArrayList<>();
//        for (Livro livro : listLivros) {
//            LivroOutputDTO dto = new LivroOutputDTO();
//            dto.setTitulo(livro.getTitulo());
//            dto.setDataLancamento(livro.getDataLancamento());
//            dto.setNumeroPaginas(livro.getNumeroPaginas());
//        }
//        return listLivrosDTO;

        
        /* lista de livros ( =dominio ),
         * Encadear algumas chamadas ( =metodo stream() ),
         * mapeia: pra cada obj Livro, chamar este construtor: LivroOutputDTO(Livro livro)
         * e passa como param o prp obj Livro. 
         * Com isso, ele converte cd obj Livro em LivroOutputDTO
         * Coleta isso numa lista: collect(Collectors.toList())
         */
        //return listLivros.stream().map(LivroOutputDTO::new).collect(Collectors.toList());
        
        /* Para nao ter q criar construtor com obj Livro na classe LivroOutputDTO
         * so p/ converter os objs, basta usar modelmapper.org. 
         * Este mapeador (conversor) requer um construtor vazio da classe dominio (Livro). 
         */
        return listLivros.stream()
                .map(t->modelMapper.map(t, LivroOutputDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public void cadastrar(@RequestBody @Valid LivroInputDTO dto) {
        
//        Livro livro = new Livro( 
//                dto.getTitulo(),
//                dto.getDataLancamento(),
//                dto.getNumeroPaginas(),
//                dto.getAutor());
//        listLivros.add(livro);
        
        Livro livro = modelMapper.map(dto, Livro.class);
        listLivros.add(livro);
    }

}
