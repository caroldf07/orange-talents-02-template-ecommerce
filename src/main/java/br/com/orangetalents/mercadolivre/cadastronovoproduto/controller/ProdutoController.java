package br.com.orangetalents.mercadolivre.cadastronovoproduto.controller;

import br.com.orangetalents.mercadolivre.cadastranovousuario.model.Usuario;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.NovoProdutoRequest;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.model.Produto;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.validacao.CaracteristicaComNomeIgualValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;


//Carga de 5
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @PersistenceContext
    EntityManager em;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(new CaracteristicaComNomeIgualValidator());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Produto> criar(@AuthenticationPrincipal Usuario usuarioLogado, @RequestBody @Valid NovoProdutoRequest novoProdutoRequest) {

        Produto produto = novoProdutoRequest.toModel(em, usuarioLogado);
        em.persist(produto);

        return ResponseEntity.ok(produto);
    }
}
