package br.com.alura.forum.controller.form;

import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class TopicoForm {

    @NotBlank @Length(min = 5)
    private String titulo;

    @NotBlank @Length(min = 10)
    private String mensagem;

    @NotBlank
    private String nomeCurso;

    public Topico convert(CursoRepository cursoRepository) {
        Topico topico = new Topico(
                this.titulo,
                this.mensagem,
                cursoRepository.findByNome( this.nomeCurso ));
        return topico;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }
}
