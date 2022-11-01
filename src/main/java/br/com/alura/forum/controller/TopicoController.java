package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

// O @RestController já diz que todos os métodos vão retonar @ResponseBody
@RestController
@RequestMapping("topico")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @GetMapping("lista-todos")
    public List<TopicoDto> listAll() {
        List<Topico> topicos = topicoRepository.findAll();
        return TopicoDto.convertList( topicos );
    }
}
