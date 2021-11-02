package br.com.alura.livraria.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class AutorControllerTest {

    @Autowired
    private MockMvc mvc;
    
    @Test
    void naoDeveriaCadastrarAutorComDadosIncompletos() throws Exception {
        String json = "{}";
        
        mvc
        .perform(
                post("/autores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
        .andExpect(status().isBadRequest());
    }

    @Test
    void deveriaCadastrarAutorComDadosCompletos() throws Exception {
        String json =
                "{\"nome\":\"Alexandre\"," + 
                "\"email\":\"ale@email.com\"," + 
                "\"dataNascimento\":\"2010-09-07\"," + 
                "\"miniCurriculo\":\"bla bla bla\"}";
        
        String jsonRet =
                "{\"nome\":\"Alexandre\"," + 
                "\"email\":\"ale@email.com\"," + 
                "\"dataNascimento\":\"2010-09-07\"}";

        mvc
        .perform(
                post("/autores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
        .andExpect(status().isCreated())
        .andExpect(header().exists("Location"))
        .andExpect(content().json(jsonRet));
    }
}
