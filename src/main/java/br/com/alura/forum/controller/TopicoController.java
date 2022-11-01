package br.com.alura.forum.controller;

import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Topico;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("topico")
public class TopicoController {

    @GetMapping("lista-todos")
    @ResponseBody
    public List<Topico> listAll() {
        Topico topico = new Topico("Dúvida", "Duvida com Spring", new Curso("Spring", "Programação"));
        return Arrays.asList(topico, topico, topico);
    }
}
