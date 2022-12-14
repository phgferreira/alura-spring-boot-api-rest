package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.dto.TopicoDtoDetalhe;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.controller.form.UpdateTopicoForm;
import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

// O @RestController já diz que todos os métodos vão retonar @ResponseBody
@RestController
@RequestMapping("topico")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    // @RequestBody significa que os dados do TopicoForm virão pelo corpo da requisição
    // O ResponseEntity<TopicoDto> é para retornar um objeto TopicoDto dentro do corpo da respota
    // Caso não queira intervir no retorno de erro não precisa usar o BindingResult como parâmetro, o próprio Spring retorna 400 Bad Request
    public ResponseEntity<TopicoDto> insert(@RequestBody @Valid TopicoForm topicoForm, UriComponentsBuilder uriBuilder) {
        Topico topico = topicoForm.convert( cursoRepository );
        topicoRepository.save( topico );

        URI uri = uriBuilder.path("/topico/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created( uri ).body( new TopicoDto( topico ) );
    }

    @GetMapping("{id}")
    public ResponseEntity<TopicoDtoDetalhe> findById(@PathVariable Long id) {
        Optional<Topico> topicoOpcional = topicoRepository.findById(id);
        if(!topicoOpcional.isPresent())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok( new TopicoDtoDetalhe( topicoOpcional.get() ) );
    }

    @PutMapping("{id}")
    @Transactional
    // O Transactional é para comitar a atualização no final do método
    public ResponseEntity<TopicoDto> update(@PathVariable Long id, @RequestBody @Valid UpdateTopicoForm topicoForm) {
        Optional<Topico> topicoOpcional = topicoRepository.findById(id);
        if(!topicoOpcional.isPresent())
            return ResponseEntity.notFound().build();

        Topico topico = topicoForm.update(id, topicoRepository);

        return ResponseEntity.ok( new TopicoDto( topico ) );
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<Topico> topicoOpcional = topicoRepository.findById(id);
        if(!topicoOpcional.isPresent())
            return ResponseEntity.notFound().build();

        topicoRepository.deleteById(id);

        return ResponseEntity.ok().build();
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
