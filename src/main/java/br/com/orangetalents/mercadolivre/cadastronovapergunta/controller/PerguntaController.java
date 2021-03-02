package br.com.orangetalents.mercadolivre.cadastronovapergunta.controller;

import br.com.orangetalents.mercadolivre.cadastranovousuario.model.Usuario;
import br.com.orangetalents.mercadolivre.cadastronovapergunta.NovaPerguntaRequest;
import br.com.orangetalents.mercadolivre.cadastronovapergunta.envionotificao.Emails;
import br.com.orangetalents.mercadolivre.cadastronovapergunta.model.Pergunta;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;


//Carga de 4
@RestController
@RequestMapping("/produtos")
public class PerguntaController {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private Emails emails;

    @PostMapping("/{id}/perguntas")
    @Transactional
    public ResponseEntity<String> criar(@PathVariable("id") Long id, @RequestBody @Valid NovaPerguntaRequest novaPerguntaRequest,
                                        @AuthenticationPrincipal Usuario usuarioLogado) {
        Usuario pessoaInteressada = em.find(Usuario.class, usuarioLogado.getId());
        Produto produto = em.find(Produto.class, id);

        Pergunta pergunta = novaPerguntaRequest.toModel(pessoaInteressada, produto);
        em.persist(pergunta);


        /*
         * Outro ponto para ter sido criado e utilizado uma instancia de e-mail é por conta da carga intrínseca
         * */
        emails.novaPergunta(pergunta);

        return ResponseEntity.ok(produto.getPerguntas().toString());
    }
}
