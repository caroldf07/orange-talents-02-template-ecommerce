package br.com.orangetalents.mercadolivre.cadastronovaopiniao.controller;

import br.com.orangetalents.mercadolivre.cadastranovousuario.model.Usuario;
import br.com.orangetalents.mercadolivre.cadastronovaopiniao.NovaOpiniaoRequest;
import br.com.orangetalents.mercadolivre.cadastronovaopiniao.model.Opiniao;
import br.com.orangetalents.mercadolivre.cadastronovoproduto.model.Produto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class OpiniaoController {

    @PersistenceContext
    EntityManager em;

    @PostMapping("/{id}/opiniao")
    @Transactional
    public ResponseEntity<String> criar(@PathVariable("id") Long id, @RequestBody @Valid NovaOpiniaoRequest novaOpiniaoRequest, @AuthenticationPrincipal Usuario usuarioAutenticado) {
        Produto produto = em.find(Produto.class, id);
        Usuario usuarioLogado = em.find(Usuario.class, usuarioAutenticado.getId());

        Opiniao opiniao = novaOpiniaoRequest.toModel(produto, usuarioLogado);
        em.persist(opiniao);

        return ResponseEntity.ok(opiniao.toString());

    }
}
