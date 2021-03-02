package br.com.orangetalents.mercadolivre.cadastronovapergunta.controller;

import br.com.orangetalents.mercadolivre.cadastranovousuario.model.Usuario;
import br.com.orangetalents.mercadolivre.cadastronovapergunta.NovaPerguntaRequest;
import br.com.orangetalents.mercadolivre.cadastronovapergunta.model.Pergunta;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.model.Produto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class PerguntaController {

    @PersistenceContext
    EntityManager em;

    @PostMapping("/{id}/perguntas")
    @Transactional
    public String criar(@PathVariable("id") Long id, @RequestBody @Valid NovaPerguntaRequest novaPerguntaRequest,
                        @AuthenticationPrincipal Usuario usuarioLogado) {
        Usuario pessoaInteressada = em.find(Usuario.class, usuarioLogado.getId());
        Produto produto = em.find(Produto.class, id);

        Pergunta pergunta = novaPerguntaRequest.toModel(pessoaInteressada, produto);
        em.persist(pergunta);

        return pergunta.toString();
    }
}
