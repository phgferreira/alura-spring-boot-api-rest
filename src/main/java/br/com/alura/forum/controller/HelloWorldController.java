package br.com.alura.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {

    @GetMapping("hello")
    // Essa anotação diz que o conteúdo retornado é um corpo e não uma página
    @ResponseBody
    public String helloWorld() {
        return "Hello World!";
    }
}
