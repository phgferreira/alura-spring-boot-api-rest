package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

// O @RestController já diz que todos os métodos vão retonar @ResponseBody
@RestController
@RequestMapping("topico")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    // @RequestBody significa que os dados do TopicoForm virão pelo corpo da requisição
    public ResponseEntity<TopicoDto> insert(@RequestBody TopicoForm topicoForm, UriComponentsBuilder uriBuilder) {
        Topico topico = topicoForm.convert( cursoRepository );
        topicoRepository.save( topico );

        URI uri = uriBuilder.path("/topico/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created( uri ).body( new TopicoDto( topico ) );
    }

    @GetMapping("lista-todos")
    public List<TopicoDto> listAll() {
        List<Topico> topicos = topicoRepository.findAll();
        return TopicoDto.convertList( topicos );
    }

    @GetMapping("lista-titulo")
    public List<TopicoDto> listByTitulo(String titulo) {
        List<Topico> topicos = topicoRepository.findByTitulo(titulo);
        return TopicoDto.convertList( topicos );
    }

    @GetMapping("lista-curso-nome")
    public List<TopicoDto> listByCursoNome(String cursoNome) {
        List<Topico> topicos = topicoRepository.findByCurso_Nome(cursoNome);
        return TopicoDto.convertList( topicos );
    }

}
