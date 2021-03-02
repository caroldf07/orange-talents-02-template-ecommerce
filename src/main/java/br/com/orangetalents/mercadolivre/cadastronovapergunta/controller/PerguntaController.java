package br.com.orangetalents.mercadolivre.cadastronovapergunta.controller;

import br.com.orangetalents.mercadolivre.cadastronovapergunta.NovaPerguntaRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class PerguntaController {

    @PostMapping("/{id}/perguntas")
    public String criar(@PathVariable("id") Long id, @RequestBody @Valid NovaPerguntaRequest novaPerguntaRequest){

        return "criando...";
    }
}
