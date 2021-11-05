package br.com.alura.livraria.service;

import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.livraria.dto.UsuarioDto;
import br.com.alura.livraria.dto.UsuarioFormDto;
import br.com.alura.livraria.modelo.Usuario;
import br.com.alura.livraria.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Page<UsuarioDto> listar(Pageable paginacao) {
        Page<Usuario> usuarios = usuarioRepository.findAll(paginacao);
        return usuarios.map(t -> modelMapper.map(t, UsuarioDto.class));
    }

    @Transactional
    public UsuarioDto cadastrar(UsuarioFormDto dto) {
        Usuario usuario = modelMapper.map(dto, Usuario.class);
        
        String senha = new Random().nextInt(999999) + "";
        usuario.setSenha(bCryptPasswordEncoder.encode(senha));
        
        usuarioRepository.save(usuario);
        
        return modelMapper.map(usuario, UsuarioDto.class);
    }

}